package com.cdzic.zicTemplate;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import sun.misc.Regexp;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ZicTemplateApplicationTests {

//    @Autowired
//    SysConfigService sysConfigService;
//    @Autowired
//    SysUserService sysUserService;
//    @Autowired
//    SysRoleService sysRoleService;
//    @Autowired
//    SysPermissionService sysPermissionService;
//    @Autowired
//    MailService mailService;

//    @Autowired
//    private StudentRepo studentRepo;
//    @Autowired
//    private StudentParentRepo studentParentRepo;
//    @Autowired
//    private StudentPayRepo studentPayRepo;

    private RestTemplate template = new RestTemplate();

//    @Autowired
//    private StudentRepo studentRepo;

//    public static void main(String[] args) throws IOException {
//        System.out.println(Pattern.matches("^[0-9]+$","1312"));

//        Thumbnails.of("C:\\Users\\Administrator\\Desktop\\360wallpaper.jpg")
//                .scale(1f)
//                .outputQuality(0.5f)
//                .toFile("C:\\Users\\Administrator\\Desktop\\线上仙德静态图片");
//    }

//    @Test
    public void test() {


//        System.out.println("进入测试方法！");
//        //随机获取6位整数
//        String code = "";
//        for (int i = 0; i < 6; i++) {
//            code += (int) (Math.random() * 10);
//        }
//        System.out.println("输出code=" + code);
//
//        //非线程延时方法
//        try {
//            Robot robot = new Robot();
//            System.out.println("延时前：" + new Date().toString());
//            robot.delay(20000);
//            System.out.println("延时后：" + new Date().toString());
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }

//        WechatPayController wechatPayController = new WechatPayController();
//        wechatPayController.wechatPlaceOrderInterface(1);

//        JSONObject httpsRequest=JSONObject.fromObject("{\"key\":\"kjk\"}");
//        JSONObject httpsRequest=null;
//        try {
//            httpsRequest.getString("key");
//        } catch (Exception e) {
//            System.out.println(12);
//        }

//        System.out.println(Base64.decode("pyyX1c5x2f0LZZ7VKZXjKO==").length);

        //登录密码
//        System.out.println(CryptoUtils.encodeMD5("123456"));


//        ResponseEntity<String> forEntity = template.getForEntity("http://192.168.31.175:8080/api/v3/brokers", String.class);
//        System.out.println(forEntity);
//            JSONObject reqJson=new JSONObject();
//            reqJson.put("topic","/mqtt/test");
//            reqJson.put("qos",1);
//            reqJson.put("retain",true);
//            reqJson.put("client_id","sss");
//
//        for (int i = 0; i < 10; i++) {
//            reqJson.put("payload","hello"+i);
//            JSONObject jsonObject = MessageUtil.httpRequest("http://192.168.31.175:8080/api/v3/mqtt/publish",
//                    "POST", reqJson.toString(), null);
//        }


//        String jsonStr="{'ab':'1'}";
//        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
//        try {
//            String aa = jsonObject.getString("aa");
//
//        } catch (Exception e) {
//            System.out.println(1);
//        }
//        System.out.println(aa);

//        File file=new File("");
//        String parentFile = null;
//        try {
//            parentFile = file.getCanonicalPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(parentFile);
//        System.out.println(CryptoUtils.encodeMD5("123456"));
//        sysUserService.modifyRole("1053818949", new String[]{"角色1"});
//        System.out.println(sysUserService.findByAccount("1053818949"));

//        List<SysRole> list = new ArrayList<>();
//        list.add(new SysRole("1", "1"));
//        System.out.println(list);

    }

    //    @Test
    public void test1() {

//        List<Student> students=new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            students.add(new Student(Long.parseLong(i+1+""),"学生"+i,0,5000.));
//        }
//
//        studentRepo.saveAll(students);

//        Student student = studentRepo.save(new Student(6320l, "张三", 0, 1000.0, null, null));
//
//        studentParentRepo.save(new StudentParent("张日", "123", "父亲", student));
//         studentPayRepo.save(new StudentPurchaseRepo("购物", true, 12.5, new Date(), student));
//        Student student1 = studentRepo.findFirstByName("张三");
//        List<StudentParent> studentParentList=new ArrayList<>();
//        List<StudentPurchaseRepo> studentPayList=new ArrayList<>();
//        studentParentList.add(studentParent);
//        studentPayList.add(studentPay);
//        student.setStudentParents(studentParentList);
//        student.setStudentPays(studentPayList);
//        System.out.println(studentRepo.save(student1));


//        try {
//            FileInputStream stream = new FileInputStream("C:\\Users\\cdzic\\Desktop\\1.xlsx");
//            XSSFWorkbook workbook = new XSSFWorkbook(stream);//读取现有的Excel
//            XSSFSheet sheet = workbook.getSheetAt(0);//得到指定名称的Sheet
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    System.out.print(cell + "\t");
//                }
//                System.out.println();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//    mailService.sendSimple("yt1053818949@sina.cn","测试邮件","这是内容\nyes");
//                sysUserService.save(new SysUser("1053818948","yaotao1053","13388124102",1,new Date(),new Date()));
//        System.out.println("登陆结果1   " + sysUserService.verificationUser("1053818949", "yaotao1053"));
//        System.out.println("登陆结果2   " + sysUserService.verificationUser("1053818949", "yaotao105"));
//        System.out.println("登陆结果3   " + sysUserService.verificationUser("105381894", "yaotao105"));
//        sysUserService.disableUser("1053818949");
//        System.out.println("登陆结果4   " + sysUserService.verificationUser("1053818949", "yaotao1053"));
//        sysUserService.enableUser("1053818949");
//        System.out.println("登陆结果5   " + sysUserService.verificationUser("1053818949", "yaotao1053"));


    }

    public void test2() {
//        sysRoleService.save(new SysRole("角色1","描述"));
//        sysRoleService.save(new SysRole("角色2","描述"));
//        sysRoleService.save(new SysRole("角色3","描述"));


    }

    public void test3() {
//        sysPermissionService.save(new SysPermission("权限1", "描述"));
//        sysPermissionService.save(new SysPermission("权限2", "描述"));
//        sysPermissionService.save(new SysPermission("权限3", "描述"));
//        List<SysPermission> all = sysPermissionService.findAll();
//        for (SysPermission sysPermission : all) {
//            System.out.println(sysPermission);
//        }
    }

}
