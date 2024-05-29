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
        public static final int Idle = 0;
        public static final int Running = 1;
        public static final int Jump = 2;
        public static final int Falling = 3;


        public static int GetImagesAmount(int player_action) {
            switch (player_action) {
                case 0:
                    return 2;
                case 1:
                    return 8;
                case 2:
                    return 5;
                case 3:
                default:
                    return 1;
            }
        }
    }
}
