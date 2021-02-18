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
package net.rptools.tokentool.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rptools.tokentool.AppConstants;
import net.rptools.tokentool.controller.GenerateBundle_Controller;
import net.rptools.tokentool.controller.TokenTool_Controller;
import net.rptools.tokentool.util.I18N;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class GenerateBundle {
  private static final Logger log = LogManager.getLogger(GenerateBundle.class);

  private Stage stage;

  public GenerateBundle(TokenTool_Controller tokenTool_Controller) {
    try {
      FXMLLoader fxmlLoader =
          new FXMLLoader(
              getClass().getResource(AppConstants.GENERATE_FXML),
              ResourceBundle.getBundle(AppConstants.TOKEN_TOOL_BUNDLE));
      Parent root = (Parent) fxmlLoader.load();
      ((GenerateBundle_Controller)fxmlLoader.getController()).setParent(tokenTool_Controller);

      stage = new Stage();
      Scene scene = new Scene(root);

      stage.getIcons().add(new Image(getClass().getResourceAsStream(AppConstants.TOKEN_TOOL_ICON)));
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.resizableProperty().setValue(false);
      stage.setTitle(I18N.getString("TokenTool.generate.bundle.title"));
      stage.setScene(scene);

      stage.show();
    } catch (Exception e) {
      log.error("Error loading GenerateBundle stage!", e);
    }
  }
}
