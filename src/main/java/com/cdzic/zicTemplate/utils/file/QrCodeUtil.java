//package com.cdzic.zicTemplate.utils.file;
//
//import com.cdzic.zicTemplate.utils.CommonUtil;
//import com.google.zxing.*;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.common.HybridBinarizer;
//import com.google.zxing.qrcode.QRCodeReader;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.Hashtable;
//
///**
// * @creator yaotao
// * @date 2018/11/20 11:27
// * @describe:
// */
//public class QrCodeUtil {
//    private static final Logger LOGGER= LogManager.getLogger(QrCodeUtil.class);
//    /**
//     * 生成包含字符串信息的二维码图片
//     * @param filePath 文件输出流路径
//     * @param content 二维码携带信息
//     * @param qrCodeSize 二维码图片大小
//     * @param imageFormat 二维码的格式
//     * @throws WriterException
//     * @throws IOException
//     */
//    public static boolean createQrCode(String filePath, String content, int qrCodeSize, String imageFormat){
//        try {
//            content=new String(content.getBytes("UTF-8"),"ISO-8859-1");
//            //设置二维码纠错级别ＭＡＰ
//            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
//            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // 矫错级别
//            QRCodeWriter qrCodeWriter = new QRCodeWriter();
//            //创建比特矩阵(位矩阵)的QR码编码的字符串
//            BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
//            // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
//            int matrixWidth = byteMatrix.getWidth();
//            //开始在缓冲图片中画出二维码
//            BufferedImage image = new BufferedImage(matrixWidth-200, matrixWidth-200, BufferedImage.TYPE_INT_RGB);
//            image.createGraphics();
//            Graphics2D graphics = (Graphics2D) image.getGraphics();
//            graphics.setColor(Color.WHITE);
//            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
//            // 使用比特矩阵画并保存图像
//            graphics.setColor(Color.BLACK);
//            for (int i = 0; i < matrixWidth; i++){
//                for (int j = 0; j < matrixWidth; j++){
//                    if (byteMatrix.get(i, j)){
//                        graphics.fillRect(i-100, j-100, 1, 1);
//                    }
//                }
//            }
//            File file = new File(filePath);
//            if (!file.getParentFile().exists()) {
//                file.getParentFile().mkdirs();
//            }
//            OutputStream outputStream=new FileOutputStream(file);
//            return ImageIO.write(image, imageFormat, outputStream);
//        }catch (Exception e){
//            LOGGER.error("创建二维码失败，原因："+e.toString());
//            return false;
//        }
//    }
//
//    /**
//     * 读二维码并输出携带的信息
//     */
//    public static void readQrCode(InputStream inputStream) throws IOException{
//        //从输入流中获取字符串信息
//        BufferedImage image = ImageIO.read(inputStream);
//        //将图像转换为二进制位图源
//        LuminanceSource source = new BufferedImageLuminanceSource(image);
//        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//        QRCodeReader reader = new QRCodeReader();
//        Result result = null ;
//        try {
//            result = reader.decode(bitmap);
//        } catch (ReaderException e) {
//            e.printStackTrace();
//        }
////        System.out.println(result.getText());
//    }
//
//
//    /**
//     * 测试代码
//     * @throws WriterException
//     */
//    public static void main(String[] args) throws IOException, WriterException {
////        createQrCode(new FileOutputStream(new File("d:\\qrcode.jpg")),"china is good",900,"JPEG");
////        System.out.println(createQrCode("C:\\Users\\Administrator\\Desktop\\34.jpg","http://www.baidu.com",900,"JPEG"));
////        System.out.println("程序执行完毕！");
////        readQrCode(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\34.jpg")));
//    }
//}
