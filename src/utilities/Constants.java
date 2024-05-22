package utilities;

import main.Game;

public class Constants {
    public Constants() {
    }

    public static class Ui{
        public static class ObjectConstants{
            public static final int spike = 1;
            public static final int spikeSizeDefault = 32;
            public static final int SpikeWidth = (int) (Game.tileScale * spikeSizeDefault);
            public static final int SpikeHeight = (int) (Game.tileScale * spikeSizeDefault);

        }
        public static class MenuButton {
            public static final int buttonDefaultWidth = 140;
            public static final int buttonDefaultHeight = 56;
            public static final int buttonWidth = (int) (buttonDefaultWidth * Game.tileScale);
            public static final int buttonHeight = (int)(buttonDefaultHeight   * Game.tileScale);
        }
        public static class PauseButton {
            public static final int soundButtonDefaultSize = 42;
            public static final int soundButtonSize = soundButtonDefaultSize * (int)Game.tileScale;
        }
        public static class UnpauseRestartMenuButton{
            public static final int unpauseRestartMenuButtonDefaultSize = 56;
            public static final int unpauseRestartMenuButtonSize = unpauseRestartMenuButtonDefaultSize * (int) Game.tileScale;
        }
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;


        public static int GetImagesAmount(int player_action) {
            switch (player_action) {
                case 0:
                    return 5;
                case 1:
                    return 6;
                case 2:
                case 6:
                case 7:
                case 8:
                    return 3;
                case 3:
                default:
                    return 1;
                case 4:
                    return 2;
                case 5:
                    return 4;
            }
        }
    }
}
