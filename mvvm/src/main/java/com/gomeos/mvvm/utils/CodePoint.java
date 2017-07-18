package com.gomeos.mvvm.utils;

/**
 * Code Point 相关计算的工具集
 * <p>
 * 由于Java语言中，一个字符并不等于一个文字，部分罕用汉字及世界上部分少数民族语言文字需要用2个字符表示一个文字。
 * 所以，当需要处理海外文本数据时，需要使用此类中的方法。
 * <p>
 * Created by chenbaocheng on 17/2/9.
 */
public final class CodePoint {
    /**
     * 计算一个字符串中有多少个文字
     *
     * @param text 文本
     * @return 文字数
     */
    public static int length(String text) {
        return text.codePointCount(0, text.length());
    }

    /**
     * 获取子字符串
     *
     * @param text  文本
     * @param start 起始文字下标
     * @return 从起始文字开始直到结尾的结果文本
     */
    public static String substring(String text, int start) {
        return substring(text, start, text.length());
    }

    /**
     * 获取子字符串
     *
     * @param text  文本
     * @param start 起始文字下标
     * @param end   终止文字下标
     * @return 结果文本。注意，end对应文字不计入结果，所以结果文本中只有 end-start 个文字
     */
    public static String substring(String text, int start, int end) {
        if (text == null) {
            throw new NullPointerException("text cannot be null.");
        }
        if (start > end) {
            throw new StringIndexOutOfBoundsException("start index cannot be greater than end index.");
        }

        char[] chars = text.toCharArray();
        int count = text.length();
        for (int i = 0; i < chars.length - 1; ++i) {
            if (i >= end) {
                break;
            }
            if (Character.isSurrogatePair(chars[i], chars[i + 1])) {
                count--;
                end++;
                if (i < start) {
                    start++;
                }
            }
        }

        if (start > count - 1) {
            throw new StringIndexOutOfBoundsException(start);
        }

        if (end > count) {
            throw new StringIndexOutOfBoundsException(end);
        }

        return text.substring(start, end);
    }
}
