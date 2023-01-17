package com.example.ContractManager.controller;

import com.example.ContractManager.model.*;
import com.example.ContractManager.repository.*;
import com.example.ContractManager.service.FileLocationService;
import com.example.ContractManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/crd")
public class CrudController {
//    @PersistenceContext
    EntityManager entityManager;

    ContractRepository contractRepository;
    ContragentRepository contragentRepository;
    StoreRepository storeRepository;
    ImageRepository imageRepository;
    RekvisityRepository rekvisityRepository;
    RoleRepository roleRepository;
    List<Contract> contracts = new ArrayList<>();
    List<Contragent> contragents = new ArrayList<>();
    List<Store> stores = new ArrayList<>();
    List<Contract> contragentFilter = new ArrayList<>();

    Boolean active = false;
    UserService userService;

    public Boolean isAdmin(User user) {
        Boolean isadmin=false;
        for (Role role: user.getRoles()) {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                isadmin=true;
            }

        }
        return isadmin;
    }



    @Autowired
    public CrudController(ContractRepository contractRepository, ContragentRepository contragentRepository, StoreRepository storeRepository, ImageRepository imageRepository,
                          EntityManager entityManager, RekvisityRepository rekvisityRepository, RoleRepository roleRepository, UserService userService) {
        this.contractRepository = contractRepository;
        this.contragentRepository = contragentRepository;
        this.storeRepository = storeRepository;
        this.imageRepository = imageRepository;
        this.entityManager = entityManager;
        this.rekvisityRepository = rekvisityRepository;
        this.roleRepository=roleRepository;
        this.userService=userService;
    }

    @Autowired
    FileLocationService fileLocationService;

    @PostMapping("/image")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws Exception {
        setActive(true);
        fileLocationService.save(image.getBytes(), image.getOriginalFilename());
        return "redirect:/crd/contracts";
    }


    @GetMapping("/contracts")
    public String getAll(ModelMap modelMap) {
        contracts.clear();
        contragents.clear();
        stores.clear();
        contractRepository.findAll().forEach(contracts::add);
        contragentRepository.findAll().forEach(contragents::add);
        storeRepository.findAll().forEach(stores::add);
        User thisUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!contragentFilter.isEmpty()){
            modelMap.addAttribute("contracts", contragentFilter);
        } else {
            modelMap.addAttribute("contracts", contracts);

        }
        modelMap.addAttribute("nomerFilter", contracts.stream().map(Contract::getNomer).filter(Objects::nonNull).distinct().collect(Collectors.toList()));
        modelMap.addAttribute("nameFilter", contracts.stream().map(Contract::getName).filter(Objects::nonNull).distinct().collect(Collectors.toList()));
        modelMap.addAttribute("predmetFilter", contracts.stream().map(Contract::getPredmet).filter(Objects::nonNull).distinct().collect(Collectors.toList()));
        modelMap.addAttribute("contragents", contragents.stream().map(Contragent::getAlias).filter(Objects::nonNull).distinct().collect(Collectors.toList()));
        modelMap.addAttribute("stores", stores.stream().map(Store::getName).filter(Objects::nonNull).distinct().collect(Collectors.toList()));
        modelMap.addAttribute("newContract", new Contract());
        modelMap.addAttribute("newContraent", new Contragent());
        modelMap.addAttribute("newFilter", new Filter());
        modelMap.addAttribute("images", imageRepository.findAll());
        modelMap.addAttribute("rekvisity", rekvisityRepository.findAll().stream().map(YurLitso::getAlias).filter(Objects::nonNull).collect(Collectors.toList()));
        modelMap.addAttribute("active", active);
        modelMap.addAttribute("thisUser", thisUser);
        modelMap.addAttribute("isAdmin", isAdmin(thisUser));

//        System.out.println(contracts);
                return "contracts";

    }

    @PostMapping("/saveContract")
    public String addContract(@ModelAttribute Contract contract, @RequestParam("contragentId") String contragentId,
                                                                 @RequestParam("image1") String image,
                                                                 @RequestParam("storen") String store,
                                                                 @RequestParam("rekviz") String rekviz) {

        List<Contragent> contragents1= new ArrayList<>();
        List<ImageContract> images = new ArrayList<>();
        List<Store> stores = new ArrayList<>();
        List<YurLitso> rekvisity = new ArrayList<>();
        Contragent contragent1 = null;
        ImageContract imageContract=null;
        Store store1=null;
        YurLitso yurLitso=null;
        if (!contragentId.equals("")) {
            contragentRepository.findAll().forEach(contragents1::add);
            contragent1 = contragents1.stream()
                    .filter(e->e.getAlias().equalsIgnoreCase(contragentId))
                    .findFirst().get();
        }
        contract.setContragent(contragent1);
        if (!image.equals("") ) {
            imageRepository.findAll().forEach(images::add);
            imageContract = images.stream()
                    .filter(e->e.getName().equalsIgnoreCase(image))
                    .findFirst().get();
        }
        contract.setImage(imageContract);


        if (!store.equals("")) {
            storeRepository.findAll().forEach(stores::add);
            store1 = stores.stream().filter(s -> s.getName().equals(store)).findFirst().get();
        }
        contract.setStore(store1);
        if (!rekviz.equals("")) {
            rekvisityRepository.findAll().forEach(rekvisity::add);
            yurLitso = rekvisity.stream().filter(s -> s.getAlias().equals(rekviz)).findFirst().get();
        }
        contract.setRekvisity(yurLitso);

        if(contragentId!= null && !contragentId.equals("")&& image!=null) {
            contractRepository.save(contract);
        }
        setActive(false);
        return "redirect:/crd/contracts";
    }

    @PostMapping("/updateContract")
    @Transactional
    public String updateContract(@ModelAttribute Contract contract, @RequestParam("alias") String contragentId,
                                 @RequestParam("imageEditname") String image,
                                 @RequestParam("yrlitsoEditname") String rekvisity,
                                 @RequestParam("storeEdit") String store) {

        List<Contragent> contragents1= new ArrayList<>();
        List<ImageContract> images= new ArrayList<>();
        List<Store> stores = new ArrayList<>();
        List<YurLitso> rervisits = new ArrayList<>();
        Contragent contragent1=null;
        ImageContract imageContract=null;
        Store store1 = null;
        YurLitso yurLitso1 = null;
        contragentRepository.findAll().forEach(contragents1::add);
        imageRepository.findAll().forEach(images::add);
        storeRepository.findAll().forEach(stores::add);
        rekvisityRepository.findAll().forEach(rervisits::add);
        if (!image.equals("")&& image != null) {
           imageContract = images.stream().filter(i -> i.getName().equals(image))
                   .findFirst().get();
           contract.setImage(imageContract);
        }
        if (!contragentId.equals("")) {
            contragent1 = contragents1.stream()
                    .filter(e -> e.getAlias().equalsIgnoreCase(contragentId))
                    .findFirst().get();
            contract.setContragent(contragent1);
        }
        if (!store.equals("")&& store != null) {
            store1 = stores.stream()
                    .filter(s -> s.getName().equals(store))
                    .findFirst()
                    .get();
            contract.setStore(store1);

        }

        if (!rekvisity.equals("")&& rekvisity != null) {
            yurLitso1 = rervisits.stream()
                    .filter(s -> s.getAlias().equals(rekvisity))
                    .findFirst()
                    .get();
            contract.setRekvisity(yurLitso1);

        }


//        contractRepository.deleteById(contract.getId());
//        contractRepository.save(editcontract);
        entityManager.merge(contract);
        return "redirect:/crd/contracts";
    }

    @PostMapping("/deleteContract/{id}")
    public String deleteContract(@PathVariable Long id) {
        contractRepository.deleteById(id);
        return "redirect:/crd/contracts";
    }
//    @PostMapping("/filter")
//    public String filterAply(@ModelAttribute Filter filter) {
//        List<Contract> preFilter = contracts;
//        List<Contract> interFilter = new ArrayList<>();
//        if (!filter.getContactName().equals("")||!filter.getContactNameOr().equals("")) {
//            interFilter = preFilter.stream()
//                    .filter(e -> e.getName().equals(filter.getContactName()) || e.getName().equals(filter.getContactNameOr()))
//                    .collect(Collectors.toList());
//            preFilter.clear();
//            preFilter.addAll(interFilter);
//            interFilter.clear();
//        }
//        if (!filter.getContragentAlias().equals("")|| !filter.getContragentAliasOr().equals("")) {
//            interFilter = preFilter.stream()
//                    .filter(e -> e.getContragent().getAlias().equals(filter.getContragentAlias())|| e.getContragent().getAlias().equals(filter.getContragentAliasOr()))
//                    .collect(Collectors.toList());
//            preFilter.clear();
//            preFilter.addAll(interFilter);
//            interFilter.clear();
//        }
//        contragentFilter=preFilter
//                .stream()
//                        .distinct()
//                                .collect(Collectors.toList());
//
//
//
//
//        System.out.println(contragentFilter);
//
//
//        return "redirect:/crd/contracts";
//    }

    @PostMapping("/addContragent")
    public String addContragent(Contragent contragent) {

        contragentRepository.save(contragent);
        setActive(true);
        return "redirect:/crd/contracts";
    }
    @PostMapping("/clear")
    public String clear() {
        contragentFilter.clear();
        return "redirect:/crd/contracts";
    }



    @GetMapping("/hello")
    public String getHello(ModelMap map) {
        map.addAttribute("newUser", new User());
        map.addAttribute("users", userService.findAll());
        map.addAttribute("roles", roleRepository.findAll());
        return "hello";
    }

    @PostMapping("/saveuser")
    public String saveUser(User user, @RequestParam("rolesset") String[] roles) {
        Set<Role> rols = new HashSet<>();
        for (int i = 0; i < roles.length; i++) {
            long id = 1;
            if (roles[i].equalsIgnoreCase("USER")) {
                id=2;
            }
            rols.add(roleRepository.findById(id).get());
        }
        user.setRoles(rols);
         userService.saveUser(user);
        return "redirect:/crd/hello";
    }

    @PostMapping("/deleteuser")
    public String deleteUser(Long id) {
        userService.deleteUser(id);
        return "redirect:/crd/hello";
    }

    @PostMapping("/getFilter")
    public String filterBy(Filter filter) {
        String pre ="";
        String query = "SELECT contract from Contract contract where";
        String and = "";
        String or = "";
        String andOr = "";
        Boolean original=false;

        if (filter.getNomer()!="" && filter.getNomer() != null){
            pre+=query +and+ " contract.nomer ='"+filter.getNomer()+"'";
            and=" and";
            query="";
            or=" or";
        }

        if (filter.getRekvisity()!="" && filter.getRekvisity() != null){
            pre+=query +and+ " contract.rekvisity.alias ='"+filter.getRekvisity()+"'";
            and=" and";
            query="";
            or=" or";
        }
        if (filter.getContactName()!="" && filter.getContactName() != null){
            pre+=query +and+ " contract.name ='"+filter.getContactName()+"'";
            and=" and";
            query="";
            or=" or";
        }

        if (filter.getContragentAlias()!="" && filter.getContragentAlias() != null){
            pre+=query +and+ " contract.contragent.alias='"+filter.getContragentAlias()+"'";
            and=" and";
            query="";
            or=" or";
        }

        if (filter.getStore()!="" && filter.getStore() != null){
            pre+=query +and+ " contract.store.name='"+filter.getStore()+"'";
            and=" and";
            query="";
            or=" or";
        }

        if (filter.getPredmet()!="" && filter.getPredmet() != null){
            pre+=query +and+ " contract.predmet='"+filter.getPredmet()+"'";
            and=" and";
            query="";
            or=" or";
        }

        if (filter.getOriginal()!="" && filter.getOriginal() != null){
//            if (filter.getOriginal().equals("да")){
//            original=true;}
            pre+=query +and+ " contract.original='"+filter.getOriginal()+"'";
            and=" and";
            query="";
            or=" or";
        }

        if (filter.getRighted()!="" && filter.getRighted() != null){
//            if (filter.getRighted().equals("да")){
//                original=true;}
            pre+=query +and+ " contract.righted='"+filter.getRighted()+"'";
            and=" and";
            query="";
            or=" or";
        }

        if (filter.getDateFrom()!="" && filter.getDateFrom() != null){
            pre+=query +and+ " contract.date>='"+filter.getDateFrom()+"'";
            and=" and";
            query="";
            or=" or";
        }
        if (filter.getDateTo()!="" && filter.getDateTo() != null){
            pre+=query +and+ " contract.date<='"+filter.getDateTo()+"'";
            and=" and";
            query="";
            or=" or";
        }

        if (filter.getNomerOr()!="" && filter.getNomerOr() != null){
            pre+=query+or+andOr+ " contract.nomer ='"+filter.getNomerOr()+"'";
            andOr=" and";
            query="";
            or="";
        }

        if (filter.getRekvisityOr()!="" && filter.getRekvisityOr() != null){
            pre+=query+or+andOr+ " contract.rekvisity.alias ='"+filter.getRekvisityOr()+"'";
            andOr=" and";
            query="";
            or="";
        }
        if (filter.getContactNameOr()!="" && filter.getContactNameOr() != null){
            pre+=query+or+andOr +  " contract.name ='"+filter.getContactNameOr()+"'";
            andOr=" and";
            query="";
            or="";
        }

        if (filter.getContragentAliasOr()!="" && filter.getContragentAliasOr() != null){
            pre+=query +or+andOr+ " contract.contragent.alias='"+filter.getContragentAliasOr()+"'";
            andOr=" and";
            query="";
            or="";
        }

        if (filter.getStoreOr()!="" && filter.getStoreOr() != null){
            pre+=query+or+ andOr+ " contract.store.name='"+filter.getStoreOr()+"'";
            andOr=" and";
            query="";
            or="";
        }

        if (filter.getPredmetOr()!="" && filter.getPredmetOr() != null){
            pre+=query+or+ andOr+ " contract.predmet='"+filter.getPredmetOr()+"'";
            andOr=" and";
            query="";
            or="";

        }

        if (filter.getDateFromOr()!="" && filter.getDateFromOr() != null){
            pre+=query+or+ andOr+ " contract.date>='"+filter.getDateFromOr()+"'";
            andOr=" and";
            query="";
            or="";

        }

        if (filter.getDateToOr()!="" && filter.getDateToOr() != null){
            pre+=query+or+ andOr+ " contract.date<='"+filter.getDateToOr()+"'";
            andOr=" and";
            query="";
            or="";

        }

        if (filter.getOriginalOr()!="" && filter.getOriginalOr() != null){
            pre+=query+or+ andOr+ " contract.original='"+filter.getOriginalOr()+"'";
            andOr=" and";
            query="";
            or="";

        }

        if (filter.getRightedOr()!="" && filter.getRightedOr() != null){
            pre+=query+or+ andOr+ " contract.righted='"+filter.getRightedOr()+"'";
            andOr=" and";
            query="";
            or="";

        }

        if (!pre.equals("")) {
        contragentFilter = entityManager.createQuery(pre)
//                .setParameter(1, filter.getContactName())
//                .setParameter(2, filter.getContragentAlias())
                .getResultList();}
        return "redirect:/crd/contracts";
    }
    @PostMapping("/updateContragent")
    @Transactional
    public String updateContragent(@ModelAttribute Contragent contr) {
        Contragent contragent =
                contragentRepository.findById(contr.getId()).get();
//        contragent.setId(contr.getId());
        contragent.setAlias(contr.getAlias());
        contragent.setName(contr.getName());
        contragent.setInn(contr.getInn());
        entityManager.merge(contragent);
//        contragentRepository.deleteById(contr.getId());
//        contragentRepository.save(contragent);
        return "redirect:/crd/contracts";
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @GetMapping("pdfmerge")
    public String pdfMerge() throws IOException {

//        List<String> sources = new ArrayList<>();
//        sources.add("C:/Users/Grebnev_A/1.pdf");
//        sources.add("C:/Users/Grebnev_A/2.pdf");
//        sources.add("C:/Users/Grebnev_A/3.pdf");
//        PDFMergerUtility ut = new PDFMergerUtility();



//        for (MultipartFile s: files) {
////File f = (File) s;
//            System.out.println(new String(files.getBytes(), Charset.defaultCharset()));
//            ut.addSource(s);
//        }
//        ut.setDestinationFileName("C:/Users/Grebnev_A/test.pdf");
//        ut.mergeDocuments();
//        BufferedImage bf = ImageIO.read(files.getInputStream());


        File path = new File("C:/Users/Grebnev_A/images/"); // base path of the images

// load source images

        List<BufferedImage> bufferedImageList = new ArrayList<>();
        for (String s: listFilesUsingJavaIO("C:/Users/Grebnev_A/images")) {
            bufferedImageList.add(ImageIO.read(new File("C:/Users/Grebnev_A/images/"+s)));
        }
//        BufferedImage image = ImageIO.read(new File(path, "1.png"));
//        BufferedImage overlay = ImageIO.read(new File(path, "2.png"));
//

// create the new image, canvas size is the max. of both image sizes
//        int w = Math.max(image.getWidth(), overlay.getWidth());
//        int h = image.getHeight()+overlay.getHeight()+10;
        int w = bufferedImageList
                .stream()
                .map(e -> e.getWidth())
                .findFirst().get();
        int h = 0;
        for (int i=0; i< bufferedImageList.size(); i++) {
            h+=bufferedImageList.get(i).getHeight()+10;
        }

        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

// paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        for (int i=0; i< bufferedImageList.size(); i++) {
            g.drawImage(bufferedImageList.get(i), 0, i*bufferedImageList.get(i).getHeight()+10, null);
//            System.out.println(i+" "+bufferedImageList.get(i).getHeight() );
        }
//        g.drawImage(image, 0, 0, null);
//        g.drawImage(overlay, 0, image.getHeight()+10, null);

        g.dispose();

// Save as new image
        ImageIO.write(combined, "PNG", new File(path, "combined.png"));
        return "redirect:/crd/contracts";
    }

    public Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

}
