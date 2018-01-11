/*
Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат.

Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>

Первым параметром в метод main приходит тег. Например, "span".
Вывести на консоль все теги, которые соответствуют заданному тегу.
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле.
Количество пробелов, n, r не влияют на результат.
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нет.
Тег может содержать вложенные теги.

Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми


Требования:
1. Программа должна считывать имя файла с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое файла (используй FileReader).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна выводить в консоль все теги, которые соответствуют тегу, заданному в параметре метода main.
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        StringBuffer str = new StringBuffer("");
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while (file.ready())
            str.append(file.readLine());
        file.close();

        String tag = args[0];
        LinkedList<Integer> startTag = new LinkedList<Integer>();
        LinkedList<Integer> endTag = new LinkedList<Integer>();

        for (int i = 0; i < str.length() - tag.length() - 2; i++) {

            if (str.substring(i, i + tag.length() + 1).
                    equalsIgnoreCase("<" + tag)) // opening tag
                startTag.add(i);

            if (str.substring(i, i + tag.length() + 3).
                    equalsIgnoreCase("</" + tag + ">")) { // closing tag
                endTag.add(i + tag.length() + 3);
                if (startTag.size() == endTag.size())
                    for (int j = 0; j < startTag.size() + 1; j++)
                        System.out.println(str.substring(startTag.removeFirst(), endTag.removeLast()));
            }
        }
    }
}
