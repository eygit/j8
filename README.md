j8
======================
�v�`�F�b�N����
======================
02_08 <- zip�̉ۑ�B�����X�g���[���ɑΉ��ł��Ă��Ȃ��B
03_01 <- �z��ԍ���for���[�v�Ő������Ă��邪�A�����_�ɃL���v�`�����������Ifinal�Ƃ݂Ȃ���Ȃ����߁A���Ӗ��Ȓ��ԕϐ��Ƀf�[�^�R�s�[���Ă���B
03_10 <- �l�@�𑱂���
03_11 <- ����


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

