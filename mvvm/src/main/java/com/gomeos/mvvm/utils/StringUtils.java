package com.gomeos.mvvm.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuyuxuan on 16/4/29.
 */
public class StringUtils {

    public static final String stringLastToken(String string, char divider) {
        if (string == null)
            return null;
        int index = string.lastIndexOf(divider);
        return (index < 0 ? string : string.substring(index + 1));
    }

    public static final String stringUrlEncode(String string, String encoding) {
        try {
            return URLEncoder.encode(string, encoding);
        } catch (Exception e) {
            return null;
        }
    }

    public static final String stringUrlDecode(String string, String encoding) {
        try {
            return URLDecoder.decode(string, encoding);
        } catch (Exception e) {
            return null;
        }
    }

    public static final String[] stringListToArray(List<String> list) {
        //TODO: 为什么不用List::toArray()方法?
        //TODO: 为什么不用范型
        if (list == null){
            return null;
        }

        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }

    public static final List<String> stringArrayToList(String[] array) {
        //TODO: 为什么不用TextUtils.join()方法?
        //TODO: 为什么不用范型
        if (array == null)
            return null;
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < array.length; i++)
            res.add(array[i]);
        return res;
    }

    public static final String[] stringArrayDecode(String string, char seperator) {
        List<String> list = stringListDecode(string, seperator);
        String[] res = stringListToArray(list);//TODO: 为什么不用List::toArray()方法?
        return res;
    }

    public static final String stringArrayEncode(String[] strings, char seperator) {
        if (strings == null)
            return null;
        StringBuilder sb = new StringBuilder();//TODO: 为什么不用TextUtils.join()方法?
        for (int i = 0; i < strings.length; i++) {
            if (i > 0)
                sb.append(seperator);
            sb.append(strings[i]);
        }
        return sb.toString();
    }

    public static final String stringListEncode(List<String> strings, char seperator) {
        if (strings == null)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            if (i > 0)
                sb.append(seperator);
            sb.append(strings.get(i));
        }
        return sb.toString();
    }

    public static final ArrayList<String> stringListDecode(String string, char seperator) {
        // TODO: 为什么不用split?
        if (string == null)
            return null;
        ArrayList<String> list = new ArrayList<String>();
        int head = 0;
        while (true) {
            int tail = string.indexOf(seperator, head);
            if (tail < 0) {
                String item = string.substring(head);
                list.add(item);
                break;
            }
            String item = string.substring(head, tail);
            list.add(item);
            head = tail + 1;
        }
        return list;
    }

    public static final boolean stringToBoolean(String str, boolean def) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
            return def;
        }
    }

    public static final int stringToInt(String str, int def) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }

    public static final byte[] stringToBytes(String str, String encoding) {
        try {
            return str.getBytes(encoding);
        } catch (Exception e) {
            return null;
        }
    }

    public static final double stringToDouble(String str, double def) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return def;
        }
    }

    public static final long stringToLong(String str, long def) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return def;
        }
    }

    public static final float stringToFloat(String str, float def) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return def;
        }
    }


    public static final String floatToString(float val, int maxFractionDigis) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(maxFractionDigis);
        return numberFormat.format(val);
    }

    /**
     * 字符串是否包含中文
     *
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static final String stringMd5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte b[] = md.digest();

            StringBuffer buf = new StringBuffer("");
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            String str = buf.toString();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String bytesMd5(byte[] data) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(data);
            byte[] result = mDigest.digest();
            return bytesToHexString(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static final String bytesToHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            int val = b & 0xff;
            if (val < 0x10) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString();
    }


    public static final byte[] hexStringToBytes(String string) {
        if (string == null)
            return null;
        int len = string.length();
        if (len % 2 != 0)
            return null;
        len = len / 2;
        byte[] res = new byte[len];
        for (int i = 0; i < len; i++) {
            int bt = 0;
            int ch0 = string.charAt(i * 2);
            int ch1 = string.charAt(i * 2 + 1);

            if (ch0 >= '0' && ch0 <= '9')
                bt += ((ch0 - '0') << 4);
            else if (ch0 >= 'a' && ch0 <= 'f')
                bt += ((ch0 - 'a' + 10) << 4);
            else if (ch0 >= 'A' && ch0 <= 'F')
                bt += ((ch0 - 'A' + 10) << 4);
            else
                return null;

            if (ch1 >= '0' && ch1 <= '9')
                bt += (ch1 - '0');
            else if (ch1 >= 'a' && ch1 <= 'f')
                bt += (ch1 - 'a' + 10);
            else if (ch1 >= 'A' && ch1 <= 'F')
                bt += (ch1 - 'A' + 10);
            else
                return null;
            res[i] = (byte) bt;
        }
        return res;
    }



    public static final String sizeBKMGDesc(long size) {
        String res = "";
        long kb = 1024;
        long mb = 1024 * 1024;
        long gb = 1024 * 1024 * 1024;
        if (size >= gb) {
            float f = (float) size / gb;
            res = String.format(Locale.getDefault(), "%.1f GB", f, Locale.getDefault());
        } else if (size >= mb) {
            float f = (float) size / mb;
            res = String.format(Locale.getDefault(), f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            res = String.format(Locale.getDefault(), f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else res = String.format(Locale.getDefault(), "%d B", size);
        return res;
    }

    /**
     * 设置字体颜色大小的span
     *
     * @param text
     * @param textColor
     * @param backColor
     * @param size
     * @return
     */
    public static final SpannableString androidSizeSpan(String text, int textColor, int backColor, int size) {
        SpannableString span = new SpannableString(text);
        int len = 0;
        if (!TextUtils.isEmpty(text)) {
            len = text.length();
        }
        if (textColor != 0)
            span.setSpan(new ForegroundColorSpan(textColor), 0, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (backColor != 0)
            span.setSpan(new BackgroundColorSpan(backColor), 0, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (size != 0)
            span.setSpan(new AbsoluteSizeSpan(size), 0, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

    public static final SpannableString androidClickSpan(String text, final int textColor, int backColor, final Object tag, final View.OnClickListener listener) {
        SpannableString span = new SpannableString(text);
        int len = text.length();
        if (textColor != 0)
            span.setSpan(new ForegroundColorSpan(textColor), 0, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (backColor != 0)
            span.setSpan(new BackgroundColorSpan(backColor), 0, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (listener != null) {
            span.setSpan(new ClickableSpan() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                    ds.setColor(textColor);
                }

                @Override
                public void onClick(View view) {
                    view.setTag(tag);
                    listener.onClick(view);
                }
            }, 0, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return span;
    }
    public static final boolean stringCompare(String str1, String str2) {
        if (str1 == null && str2 == null) return true;
        else if (str1 == null || str2 == null) return false;
        else return str1.equals(str2);
    }

    public static final String stringNullDefault(String str, String def) {
        return (str == null ? def : str);
    }

    public static final boolean stringIsEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    public static final boolean stringIsNotEmpty(String str) {
        return (str != null && str.length() > 0);
    }


    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str))
            return true;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }


}
