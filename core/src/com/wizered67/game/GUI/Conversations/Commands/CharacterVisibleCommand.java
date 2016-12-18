package com.wizered67.game.GUI.Conversations.Commands;

import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import com.wizered67.game.GUI.Conversations.CharacterSprite;
import com.wizered67.game.GUI.Conversations.CompleteEvent;
import com.wizered67.game.GUI.Conversations.ConversationController;

import java.io.IOException;
//TODO CODE WAITTOPROCEED TO WAIT FOR FADING TO FINISH
/**
 * A ConversationCommand that sets the visibility of a CharacterSprite.
 * @author Adam Victor
 */
public class CharacterVisibleCommand implements ConversationCommand {
    /** The name of the CharacterSprite to modify the visibility of. */
    private String character;
    /** Whether the CharacterSprite should be visible. */
    private boolean show;
    /** How long to fade in or out the CharacterSprite. */
    private float fadeTime;
    /** Whether to wait for the fade to complete before going on to the next command. */
    private boolean wait;
    /** Creates a new CharacterVisibleCommand that sets the CharacterSprite
     * NAME's visibility to VISIBLE when executed. Waits for completion iff W.
     */
    public CharacterVisibleCommand(String name, boolean visible, float time, boolean w) {
        character = name;
        show = visible;
        fadeTime = time;
        wait = w;
    }
    /** Executes the command on the CONVERSATION CONTROLLER. */
    @Override
    public void execute(ConversationController conversationController) {
        CharacterSprite c = conversationController.sceneManager().getCharacterByName(character);
        if (c == null) {
            return;
        }
        if (fadeTime == 0) {
            c.setVisible(show);
        } else {
            float factor = show ? 1f : -1f;
            float fadePerSecond = factor / fadeTime;
            c.setFade(fadePerSecond);
        }
    }
    /** Whether to wait before proceeding to the next command in the branch. */
    @Override
    public boolean waitToProceed() {
        return false;
    }
    /** Checks whether the CompleteEvent C completes this command,
     * and if so acts accordingly. */
    @Override
    public void complete(CompleteEvent c) {

    }
    /** Outputs XML to the XML WRITER for this command. */
    @Override
    public void writeXml(XmlWriter xmlWriter) {
        try {
            xmlWriter.element("setvisible")
                    .attribute("name", character)
                    .attribute("visible", show)
                    .pop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Static method to create a new command from XML Element ELEMENT. */
    public static CharacterVisibleCommand makeCommand(XmlReader.Element element) {
        String name = element.getAttribute("name");
        boolean visible = element.getBooleanAttribute("visible", false);
        float fade = element.getFloatAttribute("fade", 0f);
        boolean wait = element.getBooleanAttribute("wait", true);
        return new CharacterVisibleCommand(name, visible, fade, wait);
    }
}
