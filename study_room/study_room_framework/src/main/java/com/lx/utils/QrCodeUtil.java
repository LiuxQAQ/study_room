package com.lx.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Base64Util;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;

@Slf4j
public class QrCodeUtil {

  public static String creatRrCode(String contents, int width, int height) {
    String binary = null;
    Hashtable hints = new Hashtable();
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
    try {
      BitMatrix bitMatrix = new MultiFormatWriter().encode(
              contents, BarcodeFormat.QR_CODE, width, height, hints);
      // 1、读取文件转换为字节数组
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      BufferedImage image = toBufferedImage(bitMatrix);
      //转换成png格式的IO流
      ImageIO.write(image, "png", out);
      byte[] bytes = out.toByteArray();

      // 2、将字节数组转为二进制
      BASE64Encoder encoder = new BASE64Encoder();
      binary = encoder.encodeBuffer(bytes).trim();
    } catch (WriterException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return binary;
  }

  /**
   * image流数据处理
   *
   * @author ianly
   */
  public static BufferedImage toBufferedImage(BitMatrix matrix) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
      }
    }
    return image;
  }


  public static void main(String[] args) {
    String binary = QrCodeUtil.creatRrCode("1", 200,200);
    System.out.println(binary);
  }
}