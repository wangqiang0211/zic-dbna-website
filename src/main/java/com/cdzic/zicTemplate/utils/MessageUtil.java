package com.cdzic.zicTemplate.utils;

import com.cdzic.zicTemplate.utils.trust.MyX509TrustManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {
    private static Logger logger = LogManager.getLogger();

    /**
     * https请求
     *
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr, Map<String, String> headMap) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            if (headMap != null) {
                for (Map.Entry<String, String> entry : headMap.entrySet()) {
                    httpUrlConn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            StringBuffer buffer = httpOrHttpsTool(httpUrlConn, requestMethod, outputStr);
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (Exception e) {
            logger.error("SSL error" + e.getMessage());
            jsonObject = null;
        }
        return jsonObject;
    }


    /**
     * https请求
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            StringBuffer buffer = httpOrHttpsTool(httpUrlConn, requestMethod, outputStr);
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (Exception e) {
            logger.error("SSL error" + e.getMessage());
        }
        return jsonObject;
    }


    /**
     *
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @param headMap
     * @return
     */
    public static String httpsRequestStr(String requestUrl, String requestMethod, String outputStr, Map<String, String> headMap) {
        StringBuffer buffer = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            if (headMap != null) {
                for (Map.Entry<String, String> entry : headMap.entrySet()) {
                    httpUrlConn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            httpUrlConn.setSSLSocketFactory(ssf);
            buffer = httpOrHttpsTool(httpUrlConn, requestMethod, outputStr);
        } catch (Exception e) {
            logger.error("SSL error" + e.getMessage());
            buffer = null;
        }
        return null == buffer ? null : buffer.toString();
    }

    /**
     * http请求
     *
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr, Map<String, String> headMap) {
        JSONObject jsonObject = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            if (headMap != null) {
                for (Map.Entry<String, String> entry : headMap.entrySet()) {
                    httpUrlConn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
//            String base64Credentials = new String(Base64.encodeBase64("appid-test:Mjg0MTMwMjkzNDUwNDIxMjMxNTA2NTI5NjUwODIwMzgyNzC".getBytes()));
//            httpUrlConn.setRequestProperty("Authorization", "Basic " + base64Credentials);
            StringBuffer buffer = httpOrHttpsTool(httpUrlConn, requestMethod, outputStr);
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("http request error ：" + e.getMessage());
            jsonObject = null;
        }
        return jsonObject;
    }

    /**
     * http请求
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            StringBuffer buffer = httpOrHttpsTool(httpUrlConn, requestMethod, outputStr);
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (Exception e) {
            logger.error("http request error ：" + e.getMessage());
        }
        return jsonObject;
    }





    public static String httpRequestStr(String requestUrl, String requestMethod, String outputStr, Map<String, String> headMap) {
        StringBuffer buffer = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            if (headMap != null) {
                for (Map.Entry<String, String> entry : headMap.entrySet()) {
                    httpUrlConn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            buffer = httpOrHttpsTool(httpUrlConn, requestMethod, outputStr);
        } catch (Exception e) {
            logger.error("http request error ：" + e.getMessage());
            buffer = null;
        }
        return null == buffer ? null : buffer.toString();
    }

    public static StringBuffer httpOrHttpsTool(HttpURLConnection httpUrlConn, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setDoInput(true);
        httpUrlConn.setUseCaches(false);
        OutputStream outputStream = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        // 设置请求方式（GET/POST）
        try {
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("utf-8"));
                outputStream.close();
            }
            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
        } catch (ProtocolException e) {
            logger.error("https or http connect error:" + e.getMessage());
            throw new RuntimeException("https server connection timed out. " + e.getMessage());
        } catch (IOException e) {
            logger.error("https or http request error:" + e.getMessage());
            throw new RuntimeException("https request error. ", e);
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStreamReader) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    /**
     * xml解析
     *
     * @param xmlStr
     * @return
     */
    public static Map<String, String> xmlStr2Map(String xmlStr) {
        Map<String, String> map = new HashMap<String, String>();
        Document doc;
        try {
            doc = DocumentHelper.parseText(xmlStr);
            Element root = doc.getRootElement();
            List children = root.elements();
            if (children != null && children.size() > 0) {
                for (int i = 0; i < children.size(); i++) {
                    Element child = (Element) children.get(i);
                    map.put(child.getName(), child.getTextTrim());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * map集合转xml字符串
     *
     * @param param
     * @return
     */
    public static String map2XML(Map<String, String> param) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (Map.Entry<String, String> entry : param.entrySet()) {
            sb.append("<" + entry.getKey() + ">");
            sb.append(entry.getValue());
            sb.append("</" + entry.getKey() + ">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 获取用户ip
     *
     * @return
     */
    public static String getIP() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
            String localip = ia.getHostAddress();
            return localip;
        } catch (Exception e) {
            logger.error("获取IP异常" + e.getMessage());
            return null;
        }
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

        logger.debug("request xml =【{}】" + document.asXML());

        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }
}
