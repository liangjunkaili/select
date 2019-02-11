package com.course.selection.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

public class ZxingUtil {
    /**
     * 生成二维码
     * @param response
     * @param width 宽
     * @param height 高
     * @param format 格式
     * @param content 内容
     * @throws WriterException
     * @throws IOException
     */
    public static void createZxing(HttpServletResponse response, int width, int height, int margin, String level
            , String format, String content) throws WriterException, IOException {
        ServletOutputStream stream = null;
        try {
            QRCodeWriter writer = new QRCodeWriter();
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.valueOf(level));// 纠错等级L,M,Q,H
            hints.put(EncodeHintType.MARGIN, margin); // 边距
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE,height, width, hints);
            stream = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
        } catch (WriterException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
