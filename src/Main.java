import java.io.*;
import org.mvel2.MVEL;

public class Main {

    /** ここからプログラムが始まる */
    public static void main(String[] args) {
        System.out.println("Hello World! from Java");

        try {
            execMvel(); // MVELファイルの実行
        } catch (IOException e) {
            // MVELファイルを開く際にエラーが発生した場合
            System.out.println(e);
        }
    }

    /** /usr/src/mvelディレクトリにあるMVELファイルの処理を実行する */
    public static void execMvel() throws IOException {
        MVEL.evalFile(new File("mvel/main.mvel"), "UTF-8");
    }
}