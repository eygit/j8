j8
======================
要チェック項目
======================
02_08 <- zipの課題。無限ストリームに対応できていない。
03_01 <- 配列番号をforループで生成しているが、ラムダにキャプチャされる実質的finalとみなされないため、無意味な中間変数にデータコピーしている。
03_07 <- UnaryOperatorを使う構築方法の検討
03_10 <- 考察を続ける
03_14 <- 問題文で、リーダーはキャッシュを調べ・・・がなければリーダーを構築するの意図が理解できていない。。。
04_05 <- ラムダ式を返すとあるがどうすればよいかわかっていない。リスナにイベントが届いていないところの対応ができていない。
06_01 <- 正確には最大文字列長の管理プログラムになっている。文字で管理したほうがいいかも。
06_09 <- フィボナッチ数を指定の方法で計算する方法がわかっていない。
07_02 <- 文字列のsplit正規表現がJavaと同一では動作しない。
07_03 <- BigIntegerの正しい表示の仕方が不明
07_06 <- Windows上で動作するようにできなかったのでLinux環境に限定した
07_07 <- ProcessBuilderを利用しているものの再帰的でないのは題意に外れるかもしれない。
07_10 <- Linux上で再実行のこと。
08_04 <- まだできていない
08_09 <- 行の例で作っている。他の例も足したい。
08_12 <- JUnit環境の組み込み
08_13 <- 参考文献が必須。。。進め方を考える・・・

======================
メモ：
【ラムダ式と匿名の差異】
・クラス数が１つですむ
・インスタンスは同じく本体と匿名で２つ必要
　→VMの新命令を駆使している。本体クラスに関数として追加されている。

【バイナリ互換性】
・コンパイル時に＜引数と戻り値＞の組み合わせが決まり、そのメソッドについては互換性は保証される。
　→JavaVMレベルでは、引数が同一で戻り値の異なるメソッドを作ることはできる。
・新VMでなくなったメソッドと戻り値があれば、それは保証されない。
・p28：内部的に保存する場合→状態を持つStream操作の場合。p34のdistincedなど。
・p51: parallelではないストリームにおけるforEachメソッドは、forEachOrderedと同じ実装になっている。
　JavaDocでパラレルだけ非決定論的を明記しているのはそういう意図か。
　→java.util.stream.StreamPipeLine L578に以下の記述がある。
        @Override
        public void forEach(Consumer<? super E_OUT> action) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(action);
            }
            else {
                super.forEach(action);
            }
        }

        @Override
        public void forEachOrdered(Consumer<? super E_OUT> action) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(action);
            }
            else {
                super.forEachOrdered(action);
            }
        }

・p41: 恒等値について、ストリームが空のときにOptionalが返って良いのなら、引数１版のreduceを利用すれば良い

