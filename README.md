j8
======================
�v�`�F�b�N����
======================
02_08 <- zip�̉ۑ�B�����X�g���[���ɑΉ��ł��Ă��Ȃ��B
03_01 <- �z��ԍ���for���[�v�Ő������Ă��邪�A�����_�ɃL���v�`�����������Ifinal�Ƃ݂Ȃ���Ȃ����߁A���Ӗ��Ȓ��ԕϐ��Ƀf�[�^�R�s�[���Ă���B
03_07 <- UnaryOperator���g���\�z���@�̌���
03_10 <- �l�@�𑱂���
03_14 <- ��蕶�ŁA���[�_�[�̓L���b�V���𒲂ׁE�E�E���Ȃ���΃��[�_�[���\�z����̈Ӑ}�������ł��Ă��Ȃ��B�B�B
04_05 <- �����_����Ԃ��Ƃ��邪�ǂ�����΂悢���킩���Ă��Ȃ��B���X�i�ɃC�x���g���͂��Ă��Ȃ��Ƃ���̑Ή����ł��Ă��Ȃ��B
06_01 <- ���m�ɂ͍ő啶���񒷂̊Ǘ��v���O�����ɂȂ��Ă���B�����ŊǗ������ق������������B
06_09 <- �t�B�{�i�b�`�����w��̕��@�Ōv�Z������@���킩���Ă��Ȃ��B
07_02 <- �������split���K�\����Java�Ɠ���ł͓��삵�Ȃ��B
07_03 <- BigInteger�̐������\���̎d�����s��
07_06 <- Windows��œ��삷��悤�ɂł��Ȃ������̂�Linux���Ɍ��肵��
07_07 <- ProcessBuilder�𗘗p���Ă�����̂̍ċA�I�łȂ��̂͑�ӂɊO��邩������Ȃ��B
07_10 <- Linux��ōĎ��s�̂��ƁB
08_04 <- �܂��ł��Ă��Ȃ�
08_09 <- �s�̗�ō���Ă���B���̗�����������B
08_12 <- JUnit���̑g�ݍ���
08_13 <- �Q�l�������K�{�B�B�B�i�ߕ����l����E�E�E

======================
�����F
�y�����_���Ɠ����̍��فz
�E�N���X�����P�ł���
�E�C���X�^���X�͓������{�̂Ɠ����łQ�K�v
�@��VM�̐V���߂���g���Ă���B�{�̃N���X�Ɋ֐��Ƃ��Ēǉ�����Ă���B

�y�o�C�i���݊����z
�E�R���p�C�����Ɂ������Ɩ߂�l���̑g�ݍ��킹�����܂�A���̃��\�b�h�ɂ��Ă͌݊����͕ۏ؂����B
�@��JavaVM���x���ł́A����������Ŗ߂�l�̈قȂ郁�\�b�h����邱�Ƃ͂ł���B
�E�VVM�łȂ��Ȃ������\�b�h�Ɩ߂�l������΁A����͕ۏ؂���Ȃ��B
�Ep28�F�����I�ɕۑ�����ꍇ����Ԃ�����Stream����̏ꍇ�Bp34��distinced�ȂǁB
�Ep51: parallel�ł͂Ȃ��X�g���[���ɂ�����forEach���\�b�h�́AforEachOrdered�Ɠ��������ɂȂ��Ă���B
�@JavaDoc�Ńp�����������񌈒�_�I�𖾋L���Ă���̂͂��������Ӑ}���B
�@��java.util.stream.StreamPipeLine L578�Ɉȉ��̋L�q������B
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

�Ep41: �P���l�ɂ��āA�X�g���[������̂Ƃ���Optional���Ԃ��ėǂ��̂Ȃ�A�����P�ł�reduce�𗘗p����Ηǂ�

