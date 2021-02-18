/*
 * This software Copyright by the RPTools.net development team, and
 * licensed under the Affero GPL Version 3 or, at your option, any later
 * version.
 *
 * TokenTool Source Code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public
 * License * along with this source Code.  If not, please visit
 * <http://www.gnu.org/licenses/> and specifically the Affero license
 * text at <http://www.gnu.org/licenses/agpl.html>.
 */
package net.rptools.tokentool.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import net.rptools.tokentool.AppConstants;
import net.rptools.tokentool.AppPreferences;
import net.rptools.tokentool.client.TokenTool;
import net.rptools.tokentool.moulinette.Moulinette;
import net.rptools.tokentool.moulinette.Token;
import net.rptools.tokentool.util.I18N;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class GenerateBundle_Controller {
  private static final Logger log = LogManager.getLogger(GenerateBundle_Controller.class);

  @FXML
  private Button buttonCancel;
  @FXML
  private Button buttonOK;
  @FXML
  private TextField inputBundle;
  //@FXML
  //private TextField inputFrame;

  private TokenTool_Controller parent;

  @FXML
  void initialize() {
  }

  public void setParent(TokenTool_Controller parent) { this.parent = parent; }

  @FXML
  void action_OnCancel(ActionEvent event) {
    Stage stage = (Stage) buttonCancel.getScene().getWindow();
    stage.close();
  }

  @FXML
  void action_OnGenerate(ActionEvent event) {

    String bundleURL = inputBundle.getText();
    //String frameURL = inputFrame.getText();

    // read bundle JSON
    Moulinette moulinette = null;
    if(bundleURL.length() > 0) {
      try {
        moulinette = new Gson().fromJson(readUrl(bundleURL), new TypeToken<Moulinette>() {}.getType());

      } catch (Exception e) {
        log.error("Error downloading bundle config " + bundleURL);
      }
    } else {
      moulinette = new Moulinette("Current", "token", new ArrayList<>());
      Token[] list = new Gson().fromJson(AppPreferences.getPreference(AppPreferences.MOULINETTE_LIST, null), new TypeToken<Token[]>() {}.getType());
      moulinette.getList().addAll(Arrays.asList(list));
    }

    // generate images
    if(moulinette != null) {

      DirectoryChooser dirChooser = new DirectoryChooser();
      dirChooser.setTitle(I18N.getString("TokenTool.save.dirchooser.moulinette.title"));
      String prefsTargetFolder = AppPreferences.getPreference(AppPreferences.MOULINETTE_LAST_TARGET_FOLDER, null);
      if(prefsTargetFolder != null) {
        dirChooser.setInitialDirectory(new File(prefsTargetFolder));
      }
      File dir = dirChooser.showDialog(buttonOK.getScene().getWindow());
      if(dir != null) {
        // store into preferences
        AppPreferences.setPreference(AppPreferences.MOULINETTE_LAST_TARGET_FOLDER, dir.getAbsolutePath());
        for(Token t : moulinette.getList()) {
          File output = new File(dir.getAbsolutePath() + File.separator + t.getName() + ".png");
          writePortraitImage(t, output);
        }
      }
    }

    Stage stage = (Stage) buttonOK.getScene().getWindow();
    stage.close();
  }

  @FXML
  void action_OnClick(MouseEvent event) {
    log.info("Launching browser for URL " + AppConstants.MOULINETTE_URL);
    TokenTool.getInstance().getHostServices().showDocument(AppConstants.MOULINETTE_URL);
  }

  private void writePortraitImage(Token t, File tokenFile) {
    try {
      log.info(parent);
      parent.updatePortrait(t);
      parent.updateTokenPreviewImageView();
      parent.writeTokenImage(tokenFile);
    } catch (Exception e) {
      log.error("Unable to write token to file: " + tokenFile.getAbsolutePath(), e);
    }
  }

  private static String readUrl(String urlString) throws Exception {
    BufferedReader reader = null;
    try {
      URL url = new URL(urlString);
      reader = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuilder buffer = new StringBuilder();
      int read;
      char[] chars = new char[1024];
      while ((read = reader.read(chars)) != -1)
        buffer.append(chars, 0, read);

      return buffer.toString();
    } finally {
      if (reader != null)
        reader.close();
    }
  }
}
