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
package net.rptools.tokentool.moulinette;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Token {
  private static final Logger log = LogManager.getLogger(Token.class);

  private String name;
  private String url;
  private int offsetX;
  private int offsetY;
  private double scale;

  public Token(String name, String url, int offsetX, int offsetY, double scale) {
    this.name = name;
    this.url = url;
    this.offsetX = offsetX;
    this.offsetY = offsetY;
    this.scale = scale;
  }

  public Token(String prefs) {
    String[] el = prefs.split("##");
    if (el.length != 5) throw new IllegalStateException("Invalid preferences: " + prefs);
    this.name = el[0].trim();
    this.url = el[1].trim();
    this.offsetX = Integer.parseInt(el[2]);
    this.offsetY = Integer.parseInt(el[3]);
    this.scale = Double.parseDouble(el[4]);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getOffsetX() {
    return offsetX;
  }

  public void setOffsetX(int offsetX) {
    this.offsetX = offsetX;
  }

  public int getOffsetY() {
    return offsetY;
  }

  public void setOffsetY(int offsetY) {
    this.offsetY = offsetY;
  }

  public double getScale() {
    return scale;
  }

  public void setScale(double scale) {
    this.scale = scale;
  }

  @Override
  public String toString() {
    return this.name;
  }

  public String toJson() {
    String json = new Gson().toJson(this).toString();
    log.debug("JSON output: " + json);
    return json;
  }
}
