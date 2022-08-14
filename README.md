# MVEL-playground

プログラミング言語「MVEL」を簡単に試せる実行環境をコンテナとして提供するものです。

MVELがどういう言語か端的に表現すると、**Javaの資産を使えるのにコンパイル不要でJavaScriptのように書ける動的型付け言語**です。

Javaへ埋め込み可能なランタイム言語なので、実行環境としてはJavaのコンテナになります。

ぜひ、コンパイル作業から解放されJavaの資産を自由自在に扱える、MVELの高速な開発体験を試してみてください。

## 対象とする人と前提条件
- DockerとDocker Composeに関する基本的な知識があること
- Javaの知識は不要ですが、コンパイル言語の概念を理解していること

## クイックスタート

1. このリポジトリをgit cloneしてプロジェクトを始めます。

```shell
git clone https://github.com/y-magavel/MVEL-playground.git
```

2. ルートディレクトリに移動してコンテナイメージをビルドします。

```shell
cd MVEL-playground
```

```shell
# Docker Composeを使用してDockerイメージをビルドする
docker compose build
```

3. コンテナを立ち上げます。

```shell
docker compose up -d
```

4. コンテナの中に入り、Javaファイルをコンパイルします。

```shell
# コンテナの中に入る
% docker compose exec -it java /bin/bash

# コンテナの中でJavaファイルをコンパイルする
root@コンテナID:/usr/src# javac Main.java
```

5. コンバイルされたJavaのクラスファイルを実行します。

```shell
root@コンテナID:/usr/src# java Main

# 実行結果
Hello World! from Java
Hello World! from MVEL
```

## MVELを編集してみる
MVELのソースファイルはsrc/mvelディレクトリに配置しています。

src/mvel/main.mvel

```java
// これはMVELのソースコードです。

// HelloWorldを出力するシンプルな関数を定義
def sayHello() {
  place = "from MVEL";
  System.out.println("Hello World! " + place);
}

// 関数の呼び出し
sayHello();
```

HelloWorldを出力するシンプルな関数をサンプルとして用意しています。

MVEL-playgroundでは、基本的にこのmain.mvelファイルを編集することになります。

下記のようにimport文でJavaに存在するクラスを使用することができます。

```java
// これはMVELのソースコードです。

import java.util.Calendar; // Javaの資産をimportで使える

// JavaのCalendarを使って現在日時を出力する関数を定義
def isTime() {
  Calendar calendar = Calendar.getInstance();
  today = calendar.getTime(); // 現在日時を取得

  System.out.println(today);
}

// 関数の呼び出し
isTime();
```

詳しい文法については下記の公式ドキュメントを参照してください。

[MVEL Language Guide for 2.0](http://mvel.documentnode.com/)

## MVELを実行してみる

前述の通り、MVELはJavaと違ってコンパイルが不要です。

MVELのソースファイルを編集後、すぐに`java Main`で既にコンパイル済みのクラスファイルを実行することで動作を確認できます。

## 実行環境・ディレクトリ構成について

実行環境はJavaのDockerコンテナで動いています。

Docker Composeでローカルとコンテナのポートマッピングや、コンパイル時に参照するclasspathをコンテナの環境変数として設定していたりします。

ディレクトリ構成は下記のようになっています。

```shell
.
├── LICENSE
├── README.md
├── docker-compose.yml
├── infra
│   └── java
│       └── Dockerfile
└── src
    ├── Main.java
    ├── lib
    │   └── mvel2-2.4.14.Final.jar
    └── mvel
        └── main.mvel
```

## 謝辞
MVEL-playgroundを作成するにあたって参考にさせていただいた記事の一覧です。

- https://qiita.com/nannany/items/0cb541336f47bdb09b72
- https://yone098.hatenablog.com/entry/20081031/1225427372
- https://www.ibm.com/docs/ja/zos/2.3.0?topic=variable-define-classpath-environment-linux