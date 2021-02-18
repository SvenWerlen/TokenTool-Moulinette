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
import java.util.List;

public class Moulinette {

  private String name;
  private String type;
  private List<Token> list;

  public Moulinette(String name, String type, List<Token> list) {
    this.name = name;
    this.type = type;
    this.list = list;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Token> getList() {
    return list;
  }

  public void setList(List<Token> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
