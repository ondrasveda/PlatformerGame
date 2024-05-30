package gamestates;

public enum Gamestate {
    /**
     * possible gamestates
     */
    PLAYING, MENU, OPTIONS, QUIT;
    /**
     * current gamestate
     */
    public static Gamestate gamestate = MENU;
}
