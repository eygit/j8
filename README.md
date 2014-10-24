j8
======================
要チェック項目
======================
02_08 <- zipの課題。無限ストリームに対応できていない。
03_01 <- 配列番号をforループで生成しているが、ラムダにキャプチャされる実質的finalとみなされないため、無意味な中間変数にデータコピーしている。
03_10 <- 考察を続ける
03_11 <- 着手


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

