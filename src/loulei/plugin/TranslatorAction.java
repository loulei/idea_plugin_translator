package loulei.plugin;

import com.google.gson.Gson;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import org.apache.commons.lang.StringUtils;

import java.awt.*;

/**
 * Created by loulei on 17-1-7.
 */
public class TranslatorAction extends AnAction {

    private static final String APP_ID = "20170107000035411";
    private static final String SECURITY_KEY = "G7xFMYu2Z9P3CAu3E9Pr";

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        Project project = e.getData(PlatformDataKeys.PROJECT);
        SelectionModel model = editor.getSelectionModel();
        String selectedText = model.getSelectedText();
        if (StringUtils.isEmpty(selectedText)) {
            return;
        }
        System.out.println("selected text : " + selectedText);
        Result result = new Gson().fromJson(api.getTransResult(selectedText, "en", "zh"), Result.class);
        String translatedMsg = null;
        if (result != null) {
            translatedMsg = result.getTrans_result().get(0).getDst();
            System.out.println(translatedMsg);
//        Messages.showMessageDialog(project, translatedMsg, "result", Messages.getInformationIcon());
        }else{
            translatedMsg = "network error";
        }
        JBPopupFactory factory = JBPopupFactory.getInstance();
        BalloonBuilder builder = factory.createHtmlTextBalloonBuilder(translatedMsg, null, new JBColor(new Color(188, 238, 188), new Color(73, 120, 73)), null);
        builder.setFadeoutTime(5000);
        builder.createBalloon().show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
    }
}
