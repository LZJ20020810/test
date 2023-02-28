package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>();//存储首字母
        ArrayList<String> b = new ArrayList<>();//存储尾字母
        FileReader fr = null;
        int i = 0;
        String[] strings = new String[1000001];//存储各个单词
        String[] c = new String[100000];
        String[] d = new String[100000];


        //打开文件，将文件中的单词分离，存储到数组中
        File file=new File("src/1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        if(file.exists()) {

            String line;
            while ((line = br.readLine()) != null) {
                if (null != line) {
                    strings = line.split(" ");
                }
            }

            //若是文件中只存在一个英文单词
            if (strings.length == 1) {
                System.out.println("文件中仅有一个英文单词，无法实现词语接龙！");
                System.out.println(strings[0]);
                return;

            } else if (strings.length == 0) {
                System.out.println("文件为空！");
                return;
            }else {

                //将arr中的单词首尾分别存储到a和b中

                for (i = 0; i < Objects.requireNonNull(strings).length; i++) {
                    //存储首字母
                    c[i] = strings[i].substring(0, 1);
                    //存储尾字母
                    d[i] = strings[i].substring(strings[i].length() - 1, strings[i].length());
                }
            }

                //比较各个首尾字母是否相同
                int[] count = new int[1000000];
                StringBuilder sb = new StringBuilder();
                String[] result = new String[1000000];
                int[] ss = new int[1000000];

                for (int j = 0; j < strings.length; j++) {
                    sb.delete(0, sb.length());
                    count[j] = 1;
                    sb.append(strings[j]);
                    for (i = 0; i < strings.length - 1; i++) {
                        if (sb.substring(sb.length() - 1, sb.length()).equals(c[i + 1])) {
                            count[j]++;
                            sb.append(strings[i + 1]);
                        }
                    }
                    result[j] = sb.toString();
                }

                int max = 0;
                for (int k = 0; k < count.length; k++) {
                    if (count[k] >= max) {
                        max = count[k];
                    }
                }
                System.out.println(max);

                int sum = 0;
                String m = "";
                for (int k = 0; k < count.length; k++) {
                    if (count[k] >= sum) {
                        sum = count[k];
                        m = result[k];
                    }
                }

                System.out.println(m);

                //将最长英文子链输出到output.txt文件中
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/output.txt", true));
                bw.write(m);
                bw.flush();
                bw.close();

                //System.out.println(sb.toString());

            }else{
                System.out.println("文件不存在！");
            }

    }







}