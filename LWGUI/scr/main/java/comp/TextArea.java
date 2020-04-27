package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

/**
 * A very light weight text editor used for displaying and making simple edits
 * to text
 * 
 * @bugs
 * 
 *       when user moves cursor from first line down to a returned line,
 *       cursorLocation.x will be at 0 when it should be a 1
 * 
 * @author Robert Doneux
 * @version 0.2
 *
 */

public class TextArea extends Component {

	private int textX;
	private int textY;
	private int roundEdge;
	private int flatCursorPosition;
	private int charCount;

	private String[] lines;

	private Color background;
	private Color shadow;
	private Color boarder;

	private Point cursorLocation;

	private boolean focused;
	private boolean backwardsCheckCursor;

	private Graphics g;

	public TextArea() {
		roundEdge = 5;
		background = Color.LIGHT_GRAY;
		shadow = Color.DARK_GRAY;
		boarder = Color.BLACK;
		foreground = Color.BLACK;
		sizeEditable = true;
		cursorLocation = new Point(0, 0);
		charCount = 0;
		setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus commodo dapibus lorem, non tincidunt tellus viverra in. Quisque turpis nisl, dapibus et diam eu, faucibus imperdiet ex. Nulla eget tempus orci. Sed scelerisque vitae tellus vitae sollicitudin. Ut lacinia lorem eu orci feugiat consectetur. Maecenas ac malesuada ligula, id accumsan lorem. Mauris blandit congue tincidunt. Quisque et lacus id enim venenatis iaculis id non neque. Nullam libero nisi, ultrices ut purus in, hendrerit vulputate lorem. Aliquam gravida ex et sagittis tincidunt. Mauris vitae dictum ante, nec blandit justo. Maecenas scelerisque, ipsum nec molestie gravida, diam odio ornare nisi, nec tincidunt magna dui eget nisi. Nunc scelerisque elit nulla, vitae semper dui congue nec. Nam diam quam, egestas in erat nec, interdum fringilla nulla. Aliquam orci arcu, vestibulum nec viverra vitae, semper in justo. Morbi sodales tortor libero, quis tincidunt ligula ultrices in.\r\n" + 
				"\r\n" + 
				"Aenean id dolor id eros sagittis semper a a lectus. In odio tellus, lobortis et ultricies ac, feugiat vitae velit. Donec imperdiet efficitur euismod. Ut eu fringilla quam. Donec ex lacus, ultricies eget tempus euismod, finibus id nunc. Donec fermentum risus id sapien venenatis, a convallis nisi congue. Cras sagittis erat ac ante dapibus faucibus.\r\n" + 
				"\r\n" + 
				"Mauris orci velit, venenatis eu urna quis, fermentum accumsan tortor. Nulla in viverra nulla, quis fringilla tellus. Duis vitae velit eget mauris ullamcorper malesuada in non odio. Duis ac dui pulvinar, malesuada lectus ut, ultrices est. Pellentesque aliquam diam a neque pretium mattis. Ut quis diam maximus ipsum iaculis lacinia eget eu neque. Suspendisse eu dui lorem. Suspendisse vel ornare ex. Proin nisl mi, accumsan non turpis vel, porta congue velit. Suspendisse id pulvinar ante. Nullam dapibus, sem sed mattis mattis, erat nisi lobortis augue, quis fermentum lacus nisl ut ante. Donec eu suscipit justo. Vestibulum rhoncus urna quis ipsum ullamcorper molestie.\r\n" + 
				"\r\n" + 
				"Maecenas nec fermentum elit. Mauris tempor lacinia euismod. Ut tincidunt diam at bibendum auctor. Aenean ornare rhoncus lacinia. Sed consequat pulvinar ullamcorper. Suspendisse arcu sapien, pulvinar pulvinar condimentum quis, condimentum ac enim. Sed lorem lacus, ultricies id dignissim a, finibus id felis. Donec pharetra laoreet elementum. Pellentesque auctor ligula feugiat augue porttitor, a tincidunt turpis lacinia. Etiam ac consequat libero, a consectetur dui. Suspendisse semper urna neque, id rhoncus felis hendrerit a. Nam mattis ipsum at arcu fringilla bibendum. Praesent ac ipsum quis massa commodo viverra.\r\n" + 
				"\r\n" + 
				"Nam vehicula justo ut erat venenatis, sed laoreet nibh ultricies. Vestibulum tellus mi, eleifend sit amet feugiat ultrices, maximus at justo. Morbi eget viverra tellus, vel consectetur lorem. Ut ornare cursus enim eu varius. Maecenas scelerisque mattis ante in vulputate. Ut volutpat, sapien ut condimentum pellentesque, ligula leo fermentum augue, quis dignissim lacus purus vel est. Donec eu tortor varius nibh pretium interdum quis sed mi. Nullam lacinia orci ut egestas efficitur. Phasellus leo enim, porta quis elementum in, pretium quis libero.\r\n" + 
				"\r\n" + 
				"Sed ultrices vitae mi vel aliquam. Cras vulputate nec turpis non porttitor. Nunc in turpis dapibus, vehicula justo nec, consequat lectus. Pellentesque eu felis eget risus suscipit lobortis non at tortor. Aliquam sapien justo, volutpat sit amet justo vestibulum, facilisis venenatis quam. Mauris mattis dui ac lacus elementum luctus. Aenean imperdiet pharetra risus eu sodales. Pellentesque convallis mollis purus, in auctor orci cursus vel. Nulla nulla metus, lobortis vel ex ac, volutpat efficitur ligula. Praesent dolor dui, tincidunt vitae ultrices a, semper ac ipsum. Ut auctor elit et tortor bibendum, vel viverra purus sagittis. Nullam sollicitudin fringilla dui. Curabitur tempus, nulla quis malesuada accumsan, quam nisl consequat leo, id auctor est nibh in erat.\r\n" + 
				"\r\n" + 
				"Duis egestas, orci sit amet sollicitudin vehicula, leo nibh convallis orci, ut condimentum lorem tortor sit amet ante. Maecenas porta maximus turpis, sed vehicula nunc finibus sed. Proin vitae vestibulum lectus. Aenean tincidunt eget ipsum in accumsan. Etiam eu egestas nulla, nec placerat ante. Proin sapien nunc, facilisis tincidunt orci vitae, dignissim hendrerit libero. Etiam pharetra nibh vitae quam auctor, ut porttitor magna sollicitudin. Sed lacinia semper velit eget tristique. Curabitur tortor neque, dapibus sit amet venenatis non, efficitur et tortor. Nunc sed lectus neque. Nullam quis lorem cursus, ornare ex at, aliquet ante. Donec dapibus elementum metus, eu lacinia felis suscipit ut.\r\n" + 
				"\r\n" + 
				"Aenean velit lorem, feugiat et nulla at, lacinia laoreet nulla. Nullam a quam non neque sollicitudin mattis in id mi. Phasellus et sollicitudin lacus, non aliquet ante. Aliquam feugiat tellus ut metus rutrum eleifend. Proin vel ligula vitae mauris cursus vulputate vitae sed metus. Etiam sed sapien accumsan, rutrum odio eu, malesuada massa. Integer id condimentum turpis, vitae faucibus nulla.\r\n" + 
				"\r\n" + 
				"Sed dolor neque, consectetur vel blandit id, sodales quis erat. Fusce facilisis posuere augue ac suscipit. Quisque in dapibus ligula, vitae pellentesque odio. Nullam sollicitudin lorem eu tincidunt suscipit. Fusce euismod tristique ipsum fermentum suscipit. Integer mauris sapien, consectetur eu urna at, finibus condimentum sapien. Donec a purus luctus, cursus velit vel, placerat arcu.\r\n" + 
				"\r\n" + 
				"Vestibulum ultrices pulvinar euismod. Nulla fermentum luctus elit. Nam vel feugiat risus, ac blandit sapien. Donec consequat velit dolor, at imperdiet enim feugiat sit amet. Pellentesque id vestibulum nisi. Donec molestie metus vel viverra mattis. Vivamus lacus erat, consequat nec iaculis sed, egestas eget mauris. Pellentesque et diam ut felis malesuada cursus. Quisque pulvinar ipsum ante, laoreet sagittis arcu placerat accumsan. Pellentesque facilisis eget nulla nec hendrerit. Morbi vitae orci et libero pulvinar laoreet eu ultrices neque. Ut fringilla neque vitae ornare ornare. Vivamus maximus nisl tortor, nec dictum justo sollicitudin eget. Aliquam feugiat aliquam libero, eget dictum risus molestie ut.\r\n" + 
				"\r\n" + 
				"Phasellus congue pellentesque dolor vitae porta. Fusce tempus est nulla, vitae posuere velit dictum eu. Donec nisi eros, consectetur et nunc vitae, dignissim porta libero. Duis arcu odio, mattis non ipsum sed, mollis suscipit dolor. Vestibulum luctus quam vel commodo blandit. In in dui pretium, maximus massa eget, dignissim tellus. Aliquam luctus, erat ac maximus vulputate, sapien tellus tincidunt sem, sed aliquam nibh turpis ut est. Integer vulputate, nibh in aliquam scelerisque, sapien dui sollicitudin nisi, in laoreet arcu leo quis nulla. Donec nunc neque, lobortis gravida sodales at, tempus non erat. Cras fringilla dolor sed nisl efficitur mattis. Etiam risus nisl, porttitor in molestie a, efficitur vel erat. Ut bibendum turpis sed finibus volutpat. Donec dictum dignissim ante, sed condimentum quam. Donec a hendrerit est.\r\n" + 
				"\r\n" + 
				"Suspendisse potenti. Praesent rhoncus ultrices suscipit. Integer cursus nunc eget varius pulvinar. Quisque leo metus, finibus ac posuere finibus, tristique sed sapien. Aenean vel quam sem. Nam nisl ipsum, pretium a iaculis quis, dignissim quis erat. Mauris a faucibus magna. Etiam luctus non orci finibus consectetur. Integer euismod, ante ac laoreet viverra, lectus turpis fermentum risus, molestie lobortis tortor lacus quis tellus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;\r\n" + 
				"\r\n" + 
				"Integer ut leo ullamcorper, gravida nisi tempor, faucibus nisl. Vivamus sed pellentesque est, lacinia consequat justo. Aenean ut risus eu nisi iaculis mollis. Donec eu dictum urna. Curabitur accumsan ac leo eu pharetra. Maecenas varius mauris sed nisl pharetra ornare. Mauris purus nibh, dictum id consectetur ut, imperdiet ut ipsum. Suspendisse ornare, libero ac tristique consequat, ex massa pulvinar libero, quis facilisis nibh ipsum et massa. Vivamus vehicula sem id nunc pellentesque viverra. Pellentesque eu consectetur dolor.\r\n" + 
				"\r\n" + 
				"Praesent ullamcorper enim venenatis lobortis dictum. Aliquam porttitor fermentum enim tincidunt ornare. Donec dictum suscipit neque, id sagittis neque porttitor et. Maecenas eleifend vulputate ante, ut consectetur ex consectetur eu. Etiam sed nunc scelerisque, dapibus nisl eu, congue nibh. Sed at mauris pharetra, luctus dui quis, porttitor leo. Proin ac elementum turpis. Praesent ullamcorper hendrerit ex.\r\n" + 
				"\r\n" + 
				"In nec auctor ipsum, eu congue diam. In feugiat aliquam ex. Pellentesque rutrum orci sit amet feugiat consectetur. Suspendisse turpis purus, gravida eu scelerisque id, vulputate ut quam. Mauris dolor sem, pretium eget auctor eu, pellentesque id purus. Phasellus malesuada tincidunt justo, ac fermentum sapien sagittis sed. Integer dapibus, nisi ut varius efficitur, nisl turpis sodales purus, quis convallis justo erat in nisl. Nunc porttitor sed enim sit amet pulvinar.\r\n" + 
				"\r\n" + 
				"Maecenas rutrum, metus non bibendum finibus, ex leo euismod odio, quis rhoncus nisl erat sed est. Duis eleifend urna ut condimentum porttitor. Cras vitae facilisis erat. Fusce et scelerisque massa. Ut vel nibh ullamcorper, tempus lorem vel, bibendum erat. Nullam sit amet nibh nibh. Etiam hendrerit, ligula eu tempor lobortis, tortor dui dapibus velit, ac faucibus nulla felis ut ante. Duis dignissim sagittis augue eu accumsan. Integer rutrum sem massa, id luctus lacus ullamcorper eget. Proin nec nisl quis risus vestibulum elementum. Nulla dictum magna eget ullamcorper vestibulum. Duis at ultrices nulla.\r\n" + 
				"\r\n" + 
				"Maecenas libero eros, efficitur eu magna eu, varius aliquam neque. Curabitur sit amet nibh magna. Nam sit amet dui condimentum, pulvinar massa eu, congue ante. Mauris sapien justo, viverra quis maximus eu, suscipit ut neque. Pellentesque vitae condimentum turpis, ac dapibus velit. Donec ultricies eget orci eu rutrum. Vestibulum a ligula et tellus pulvinar auctor. Sed scelerisque risus ut sapien porta, nec interdum eros gravida. Maecenas vitae laoreet justo. Curabitur nec laoreet sapien, vel varius velit. Nam in ligula fermentum, vulputate est ac, ultrices nisl. Etiam sodales elit dolor, vel tempor magna dictum ut.\r\n" + 
				"\r\n" + 
				"Ut sed orci fermentum, volutpat quam at, imperdiet purus. Sed iaculis vestibulum tincidunt. Sed dignissim vehicula ligula quis posuere. Proin sollicitudin lorem non metus sollicitudin, ut mattis ipsum molestie. Nulla ac venenatis justo. Proin nulla diam, dignissim ut tortor sed, laoreet condimentum mauris. Sed tincidunt lectus ut accumsan fermentum. Morbi pretium eget nibh id bibendum. Duis volutpat massa vitae scelerisque semper. Pellentesque vitae sagittis ligula, id interdum eros. Quisque consequat pulvinar justo, et luctus lectus dictum ut. Aenean maximus tincidunt erat, ac aliquet neque scelerisque et. Maecenas placerat urna massa, a eleifend diam commodo et. Quisque aliquet purus urna, in interdum ligula efficitur vitae. Duis posuere dui ut eleifend imperdiet. Praesent ut scelerisque nisi.\r\n" + 
				"\r\n" + 
				"In blandit mi quis lectus ultrices commodo. Aliquam erat volutpat. Duis pellentesque lorem vel mauris venenatis vehicula. Aenean interdum dapibus tempor. Vestibulum pellentesque velit id risus interdum, quis placerat urna feugiat. Fusce venenatis erat et magna posuere, eu aliquet lorem placerat. Aliquam ut cursus nibh. Fusce urna tortor, laoreet pellentesque nisi non, varius fringilla augue. Etiam sed ornare sapien.\r\n" + 
				"\r\n" + 
				"Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras bibendum sollicitudin purus, sit amet vulputate sem finibus in. Nullam molestie dolor eleifend dapibus pharetra. Nam ullamcorper, enim non fermentum commodo, risus magna facilisis purus, nec porttitor lectus felis quis sapien. Suspendisse nibh ipsum, rhoncus eu euismod nec, porta eget massa. Curabitur rutrum et sapien a lacinia. Pellentesque sapien nisl, accumsan at massa ut, volutpat tincidunt orci. Mauris faucibus ornare urna nec ornare. Proin consequat lacus in orci venenatis, eget accumsan nisi dictum.\r\n" + 
				"\r\n" + 
				"Donec accumsan, nulla vel hendrerit tempor, enim ipsum luctus ligula, sed tristique nisi libero a lacus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum in enim consequat, eleifend justo non, maximus erat. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam nec aliquet purus. Vestibulum sagittis dictum bibendum. Aenean eget nibh interdum, porta diam fringilla, convallis odio.\r\n" + 
				"\r\n" + 
				"Nullam ac augue vel lacus tincidunt vulputate id a ex. Ut non diam eu augue laoreet tincidunt. Phasellus nibh augue, hendrerit ut dapibus ac, tincidunt sit amet mi. Nulla faucibus dui imperdiet, imperdiet turpis a, accumsan lacus. Aliquam quis euismod purus, ac posuere erat. Nullam cursus eget tellus a dignissim. Nullam dolor nisl, cursus sit amet dui consectetur, venenatis tincidunt est. In lorem nunc, posuere vitae pretium ut, facilisis ut libero. Etiam non aliquam tortor. Morbi nec vulputate ipsum, ut pretium justo. Duis euismod suscipit malesuada. Sed fermentum neque risus, nec convallis turpis ultrices non. Aliquam suscipit sollicitudin mi ut dictum.\r\n" + 
				"\r\n" + 
				"Praesent nec bibendum justo. Quisque aliquam nunc ipsum, quis pretium tortor feugiat eu. Etiam interdum diam ut dui volutpat aliquam. Donec massa risus, ornare ac finibus sed, egestas sit amet leo. Maecenas ac nisl turpis. Maecenas hendrerit, risus convallis pellentesque tempor, nibh metus aliquet enim, ac mollis risus purus tempus eros. Nulla dignissim arcu vel ultricies feugiat.\r\n" + 
				"\r\n" + 
				"Vivamus tincidunt neque et lacus ultricies eleifend. Cras in quam mauris. Etiam eu auctor felis. Curabitur ut euismod odio. Vivamus semper consectetur ipsum sed scelerisque. Morbi sollicitudin, ligula id molestie rhoncus, massa justo egestas orci, non lobortis ex diam id velit. In lectus massa, lobortis ac varius nec, fermentum in ligula. Vivamus lacinia sapien id ex bibendum finibus. Maecenas non orci vitae ligula tempus hendrerit. Praesent aliquam tellus erat, eu convallis mi dignissim eget. Proin a congue ex. Phasellus elementum tincidunt euismod. Praesent non nibh dapibus, semper enim iaculis, porta est. Maecenas interdum vehicula orci eget facilisis.\r\n" + 
				"\r\n" + 
				"Suspendisse nec mi tellus. Vivamus eleifend rhoncus elit sit amet tempor. Vivamus id ultricies enim, vitae tincidunt quam. Donec velit purus, blandit sit amet hendrerit a, egestas ac augue. Sed facilisis lacinia purus eget semper. Vestibulum eleifend lacus dui, ut volutpat augue pretium auctor. Praesent at est quis lectus efficitur maximus quis ut felis. Pellentesque posuere leo non nulla semper, ut blandit lectus viverra. Nam pulvinar lacus vel molestie bibendum. Morbi maximus massa est, a aliquam erat tristique in. Cras nec ipsum eget sapien vulputate venenatis. Aenean eu mollis lacus. Duis nunc dui, mollis vel nulla in, egestas consequat elit. Nunc faucibus metus a augue hendrerit, quis fermentum velit viverra. Vestibulum in faucibus lorem, ut tristique est. Vivamus rutrum, ipsum sit amet dapibus ornare, eros augue volutpat massa, eget mattis neque est ut nunc.\r\n" + 
				"\r\n" + 
				"Donec vitae mi vitae mauris sollicitudin feugiat a nec lacus. Pellentesque finibus, dolor eu posuere pharetra, leo metus auctor sem, et malesuada tortor felis a nisl. Cras nec libero vitae urna vulputate elementum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras vehicula iaculis quam, sed tempus leo laoreet a. Morbi efficitur neque justo, ac finibus mauris consequat non. Duis tortor eros, pulvinar et odio id, ornare tincidunt sem. Mauris et diam sed libero scelerisque porta. Suspendisse varius tincidunt ligula. Vestibulum placerat consequat volutpat. Sed commodo, odio sit amet mattis vehicula, diam arcu fringilla urna, volutpat facilisis eros purus ac lectus. Maecenas aliquam finibus maximus. Nam dapibus tellus vitae dictum laoreet. Vivamus rutrum dignissim gravida. Nunc dapibus mollis metus at commodo. Integer non sem euismod justo sagittis faucibus.\r\n" + 
				"\r\n" + 
				"Sed ut porta justo, et consectetur nisl. Pellentesque facilisis sem sed semper sagittis. Nam non tincidunt felis. Donec vehicula non nibh ultricies porttitor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque mattis enim erat, ac tristique felis porta ut. Phasellus quis massa elementum, volutpat ante nec, porttitor tellus. Nulla at est finibus odio iaculis tincidunt. Nam laoreet leo at nisi malesuada condimentum. Vivamus tincidunt sodales eros, nec volutpat enim mollis id. Sed et felis diam.\r\n" + 
				"\r\n" + 
				"Pellentesque at magna at nunc ultrices blandit in vitae quam. Vestibulum neque nunc, suscipit et luctus vitae, congue quis ex. Nunc dictum tellus mauris, non fringilla nibh tristique in. Etiam vel arcu eu arcu maximus vehicula. Morbi bibendum dolor in risus tristique, vitae vestibulum sapien ultricies. Phasellus elementum malesuada neque, suscipit egestas elit scelerisque at. Nunc nec dolor ut mauris efficitur dapibus vitae at odio. Etiam gravida quam id lectus mattis bibendum. Nulla placerat risus nunc, id volutpat sapien consectetur eget. Fusce pharetra augue quis massa tincidunt, eu bibendum augue euismod.\r\n" + 
				"\r\n" + 
				"In elementum est at gravida fermentum. Proin tempor libero et nulla euismod varius. Phasellus finibus, velit eget condimentum dictum, nibh elit lobortis nulla, vitae ullamcorper arcu sem non mauris. Ut sit amet ante sed augue hendrerit laoreet. Nullam ultricies justo id nunc mollis rutrum. Maecenas et dignissim odio. Sed feugiat scelerisque suscipit. Morbi id nisi est. Duis porta tortor vitae risus efficitur tincidunt. Aenean et molestie erat. Proin consectetur sapien lacus, eget pharetra odio fermentum vitae.\r\n" + 
				"\r\n" + 
				"In a porta ipsum. In posuere nisi sed leo cursus, eget dignissim diam dapibus. In euismod commodo metus, eu varius ipsum pellentesque a. Nulla porttitor ornare diam vel pulvinar. Fusce laoreet felis ut leo sagittis molestie. Curabitur ut mattis eros, et facilisis purus. Nam volutpat, purus a lobortis rhoncus, augue felis ornare enim, vel commodo eros velit a neque. Morbi eget blandit quam.\r\n" + 
				"\r\n" + 
				"Etiam est dolor, viverra in luctus vitae, vulputate eu lorem. Nam eget nunc vestibulum, convallis orci ut, commodo lectus. Suspendisse quis ullamcorper massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras sem massa, blandit a pellentesque sed, imperdiet eget lacus. Duis ut vulputate diam, id dapibus nisl. Cras faucibus non augue ac faucibus. Quisque faucibus odio est, in elementum nunc viverra in.\r\n" + 
				"\r\n" + 
				"Donec cursus lectus et quam aliquam, non rhoncus metus interdum. Phasellus non vehicula nunc, vitae posuere risus. Morbi id facilisis est, a euismod nibh. Nulla ultrices turpis ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Mauris quis mattis purus, eu scelerisque nibh. Aenean dignissim accumsan nisi non mollis. Aenean hendrerit sodales varius. Donec lorem ex, aliquam vel varius at, mollis in massa. Vestibulum pretium interdum augue vel interdum. Donec rutrum dui et justo hendrerit sollicitudin. In non ligula nisl. Cras nec ipsum placerat, sagittis metus quis, blandit ligula. Vestibulum molestie sed elit ac viverra. Fusce vulputate, metus sed elementum suscipit, arcu elit bibendum urna, sed mollis nunc leo eget tellus. Quisque aliquet, velit in blandit tempor, lorem metus vestibulum lacus, feugiat gravida augue dui vel erat.\r\n" + 
				"\r\n" + 
				"Sed iaculis eget velit ac feugiat. Aliquam tempus, est at fermentum accumsan, turpis velit ornare ligula, sit amet pretium mi orci sed odio. Fusce quis mauris malesuada, porta ante a, gravida est. Aliquam a dictum odio. Morbi gravida, enim nec venenatis commodo, ex elit luctus neque, at mollis quam sapien eu magna. Aenean imperdiet nisi turpis, at lacinia justo ornare vel. Maecenas libero lacus, placerat ac elementum ac, consequat in ipsum. Etiam aliquam leo at ultrices venenatis. Morbi vitae mauris porttitor, maximus lacus non, pulvinar odio.\r\n" + 
				"\r\n" + 
				"Suspendisse maximus ipsum in lobortis sodales. Morbi euismod pellentesque condimentum. Integer a ante lacus. Mauris quis sem mattis, lacinia lectus id, elementum tortor. Vivamus eget malesuada est. Aliquam nisi neque, sagittis et tincidunt vel, rhoncus viverra turpis. Donec urna orci, vulputate sed volutpat sed, ornare sit amet purus. In nunc magna, sollicitudin vitae velit in, posuere rutrum mauris.\r\n" + 
				"\r\n" + 
				"Donec lobortis, lectus et pellentesque congue, orci ante maximus massa, vitae mollis ligula urna sed arcu. Suspendisse ex massa, sodales et nulla at, volutpat malesuada ante. Pellentesque fringilla, tellus eget molestie pharetra, justo ipsum tristique diam, ut sollicitudin odio risus ac mauris. Cras nibh augue, fringilla vel porttitor malesuada, laoreet non neque. Vestibulum convallis vel orci laoreet luctus. Fusce ligula sem, convallis vel aliquam condimentum, porttitor vel mauris. Duis sollicitudin semper diam nec pharetra. Donec egestas risus vel erat congue suscipit. Suspendisse nunc felis, tincidunt sed sem quis, egestas suscipit velit. Nam a risus maximus, efficitur nisl sed, bibendum erat. Curabitur rutrum quis tortor sit amet fringilla. Quisque in diam felis. Proin commodo tellus elit, eu sollicitudin nibh facilisis sit amet.\r\n" + 
				"\r\n" + 
				"Etiam eget hendrerit ante. Praesent porttitor nisl eu nulla rutrum eleifend. Mauris quis accumsan magna. Nunc euismod neque eros. Nunc rhoncus condimentum condimentum. Quisque tincidunt tristique feugiat. Maecenas non massa et orci cursus aliquet. Nulla facilisi. Nunc molestie arcu ipsum, bibendum volutpat risus consequat eget. Quisque sit amet accumsan nunc. Duis efficitur, arcu vitae volutpat fringilla, ligula mauris sagittis elit, vel volutpat libero tortor et velit.\r\n" + 
				"\r\n" + 
				"Aliquam fermentum pulvinar dui, non luctus felis porta eu. Pellentesque vestibulum gravida placerat. Sed sed venenatis libero. Nunc quis vulputate lectus. Duis facilisis, mi vel pulvinar lacinia, tellus eros maximus justo, pulvinar rhoncus magna magna quis diam. Praesent laoreet luctus arcu. Donec fringilla lectus pharetra purus blandit, eget pellentesque tortor elementum. Vestibulum fringilla tellus et mollis efficitur. Integer mattis tincidunt ante.\r\n" + 
				"\r\n" + 
				"Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Pellentesque scelerisque facilisis ex in aliquam. Pellentesque in tristique ante. Proin eleifend nulla nec enim tempus, sed sodales magna rhoncus. Nulla quam massa, ullamcorper sit amet consectetur a, mollis ut elit. Aenean sodales rutrum diam sed efficitur. In sit amet libero vel arcu euismod vehicula nec nec nisi. Donec ut volutpat metus, at maximus ex. Donec sit amet sodales eros, in volutpat metus. Nulla vitae bibendum odio, id mollis nibh. Duis dignissim finibus lobortis. In ut velit venenatis, aliquet eros nec, fringilla lectus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Cras orci magna, ullamcorper in risus eget, laoreet vulputate lorem. Phasellus ac malesuada neque, at porttitor dui.\r\n" + 
				"\r\n" + 
				"Vestibulum vehicula maximus ex, ut aliquet erat ullamcorper quis. Donec fermentum molestie ante, ac dignissim tortor aliquet at. Integer eu arcu purus. Cras et velit eu lorem vulputate fermentum. Pellentesque ac dapibus sem. Donec molestie tortor at elit molestie dictum. Aliquam lorem tellus, luctus nec turpis sed, viverra porta arcu. Aliquam rutrum scelerisque consequat.\r\n" + 
				"\r\n" + 
				"Suspendisse potenti. Proin vulputate sapien eget libero fringilla blandit. Morbi finibus turpis ut maximus luctus. Vestibulum finibus, sapien tempus auctor rutrum, magna metus tempor libero, sit amet accumsan urna lectus non risus. Nulla ultricies mi at accumsan sollicitudin. Pellentesque vel molestie erat, vitae venenatis dolor. Duis sem neque, fringilla id dolor quis, dictum interdum libero. Sed lobortis iaculis enim vel ultricies. Vestibulum hendrerit mattis velit, nec venenatis purus posuere sit amet.\r\n" + 
				"\r\n" + 
				"Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras fermentum ultricies blandit. Morbi suscipit commodo neque, vel viverra sapien pretium sit amet. Integer pellentesque dapibus erat, vitae gravida sapien placerat nec. Nulla in magna leo. Sed sodales cursus erat, eu ultricies mauris euismod id. Duis viverra, lectus sed porta pharetra, nisi lorem pulvinar tortor, sit amet gravida lectus justo eu ex. Nam ultricies neque at volutpat hendrerit. Donec auctor diam id tortor viverra aliquet.\r\n" + 
				"\r\n" + 
				"Suspendisse feugiat ullamcorper tortor in rutrum. Phasellus hendrerit, enim id ultricies sagittis, nibh felis maximus elit, vel ullamcorper ligula turpis sed nunc. Ut convallis interdum risus, id efficitur orci semper eget. Morbi id dignissim nisl. Aenean vel hendrerit sapien, ac laoreet leo. Aenean consequat velit at urna sagittis tincidunt. Aenean at ante id purus dignissim ornare et sed turpis.\r\n" + 
				"\r\n" + 
				"Etiam ultrices dolor ut erat auctor, fermentum viverra dui fermentum. Nullam eu imperdiet libero. Vestibulum iaculis turpis eu magna aliquam, vitae sollicitudin dui mollis. Sed eget volutpat nunc. Cras ullamcorper nisl at tellus fermentum auctor. In convallis, lorem non pellentesque tincidunt, felis quam ultrices tortor, eu egestas lectus justo in leo. Nulla sed tortor nec velit suscipit efficitur. Nunc sed lobortis nulla. Aliquam sed est sagittis, imperdiet tortor id, porta nibh. Donec blandit fringilla metus, sed tempor lacus sollicitudin sit amet. Donec in neque a ex semper interdum ut eget ipsum.\r\n" + 
				"\r\n" + 
				"Morbi ut enim bibendum, convallis ipsum sed, vestibulum velit. Suspendisse tempor quis sapien ut vehicula. Pellentesque et dapibus purus. Vivamus porttitor eros non massa iaculis luctus tempus vitae enim. Sed malesuada cursus aliquam. In hac habitasse platea dictumst. Pellentesque lobortis hendrerit turpis, facilisis feugiat purus facilisis eget. Aenean mattis massa tincidunt, faucibus lacus sed, convallis nisl. Aenean tellus ante, condimentum nec bibendum at, fermentum et enim. Maecenas a sapien sem.\r\n" + 
				"\r\n" + 
				"Cras tincidunt libero non ipsum aliquam, quis tristique massa venenatis. Duis maximus nec diam ac accumsan. Quisque sollicitudin condimentum commodo. Nam tristique tellus ligula, volutpat placerat magna eleifend a. Nam maximus, velit quis tincidunt aliquam, enim lorem mattis lacus, ut commodo libero massa gravida augue. Sed iaculis iaculis elementum. Mauris eget ligula nec nisl molestie rutrum ac ut magna. Praesent vel risus sit amet lectus malesuada commodo. Ut ultrices ex nec libero euismod iaculis. Quisque fermentum ante nec augue facilisis sollicitudin. Ut commodo lectus diam, a rutrum magna pulvinar lobortis.\r\n" + 
				"\r\n" + 
				"Maecenas sed sagittis ipsum. Donec ut pulvinar velit. Cras laoreet turpis non mauris malesuada faucibus. Etiam tristique, velit non ultrices finibus, lectus elit maximus nisi, nec malesuada quam mi ut ex. Aenean imperdiet risus eu leo accumsan, at placerat nisi consequat. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent egestas tempor tortor ac feugiat. Mauris mollis erat eu ex sollicitudin, eu maximus nunc porta. Vivamus neque dui, ullamcorper eu arcu id, iaculis lacinia dui. Vestibulum sed ante ac nunc posuere tempor vel et mauris.\r\n" + 
				"\r\n" + 
				"Aenean mattis, ex ut vehicula ornare, ipsum ipsum rutrum turpis, sed sollicitudin risus arcu sed sem. Integer ornare id leo in porta. Suspendisse potenti. Nunc imperdiet, sapien in porta mattis, urna enim hendrerit odio, ut consectetur velit turpis sed neque. Sed dignissim augue vitae dolor lacinia sollicitudin. In suscipit, arcu ac elementum consectetur, elit metus cursus magna, non venenatis velit magna vitae justo. Nunc ullamcorper rutrum quam vitae congue. Curabitur lectus massa, sollicitudin nec metus eget, ultrices pulvinar urna. In at felis sed nisi facilisis eleifend. Nam non vestibulum felis. Fusce pulvinar posuere sem consequat ultricies. Donec vulputate dolor ac sodales venenatis. Sed venenatis fringilla justo, sed dignissim sem iaculis a. Nullam sit amet vulputate odio, quis blandit mi.\r\n" + 
				"\r\n" + 
				"Vestibulum nec semper sem, eu luctus elit. Nulla facilisi. Vivamus commodo eros eget libero pretium aliquet. Nam facilisis pretium purus, in pulvinar dolor porta vel. Cras at erat eu dolor egestas semper at in urna. Nunc et suscipit lorem. Maecenas non metus nec mauris sodales iaculis. Cras congue pellentesque est vel venenatis. Nullam ornare metus nisl, at venenatis ipsum viverra ac. Proin blandit tortor ac efficitur ultricies. Vestibulum suscipit ac orci ut laoreet. Duis viverra consequat ipsum et feugiat. Nulla mattis felis eu sem consectetur, et pellentesque arcu posuere. Fusce sagittis condimentum libero in laoreet.\r\n" + 
				"\r\n" + 
				"Quisque sodales venenatis nunc, eu rutrum dui tincidunt a. Aliquam dapibus eros vel mattis eleifend. Morbi sed velit bibendum, molestie dolor sit amet, sodales leo. Duis mattis eget velit sed lobortis. Curabitur lacinia dapibus nisi, ac mollis arcu auctor non. Maecenas condimentum lacus et neque pretium pulvinar. Cras consequat libero et dolor pharetra luctus. Curabitur faucibus feugiat elit rhoncus vehicula. Nunc sed felis eros. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\r\n" + 
				"\r\n" + 
				"In ornare pharetra semper. Quisque sed augue sit amet ipsum eleifend tempor ac non ipsum. Quisque iaculis porttitor placerat. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis non semper eros. Nullam ultricies mi ut maximus maximus. Maecenas tincidunt est tellus, eget sagittis libero dignissim ut. Sed ante ante, scelerisque vel eros at, iaculis feugiat magna. Nam a interdum odio, eu dignissim nulla. Fusce ac tempor quam. Sed pulvinar, arcu facilisis porttitor molestie, justo ipsum rhoncus sem, eu placerat nisi est eget lectus. Sed sed nisl varius, viverra eros id, egestas sem. Nunc vestibulum ex luctus augue pretium, nec fermentum odio convallis. Phasellus commodo arcu sed imperdiet hendrerit. Nullam pellentesque interdum diam nec dignissim.");
	}

	@Override
	public void revise() {
		textX = x + 10;
		textY = y + 5;

		// System.out.println(cursorLocation.x);
		// System.out.println(flatCursorPosition);

		// set the protectedText string length to the max charCount variable. If the
		// charCount is 0, there is no maximum char count - user can input as many chars
		// as they want
		if (charCount > 0 && protectedText.length() > charCount) {
			protectedText = protectedText.substring(0, charCount);
		}
	}

	private long timer = System.currentTimeMillis();
	private boolean show;

	private void wrapString(Graphics g) {

		int lineTotal = 0;
		int line = 0;
		int lineWidth = 0;
		int lineHeight = 0;
		int textHeight = g.getFontMetrics(font).getHeight() + 2;
		String words[] = protectedText.split("\f");
		int lineBreaks = protectedText.split("\n").length;
		lines = new String[(((g.getFontMetrics().stringWidth(protectedText)) / (getBounds().width - 10)) + lineBreaks)
				* 2];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String space = " ";
			if (word.contains("\n")) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth("");
				lines[line] = sb.toString();
				lineTotal += lines[line].length();
				sb = new StringBuilder();
				line++;

			} else if (lineWidth + g.getFontMetrics().stringWidth(word) > (getBounds().width - 10)) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth("");
				lines[line] = sb.toString();
				lineTotal += lines[line].length();
				sb = new StringBuilder();
				line++;
			}

			g.setColor(foreground);
			g.drawString(word + space, textX + lineWidth, textY + g.getFontMetrics().getAscent() + lineHeight);
			lineWidth += g.getFontMetrics().stringWidth(word + space);
			sb.append(word + space);

			if (i == words.length - 1) {
				lines[line] = sb.toString();
			}

			if (backwardsCheckCursor) {
				if (line == cursorLocation.y) {
					flatCursorPosition = lineTotal + cursorLocation.x;
					backwardsCheckCursor = false;
				}
			} else {
				if (i == words.length - 1) {
					normaliseXandY(lines);
				}
			}

		}

		if (cursorLocation.x > -1) {
			int cursorX = 0;
			if (lines[cursorLocation.y] != null) {
				cursorX = g.getFontMetrics().stringWidth(lines[cursorLocation.y].substring(0, cursorLocation.x));
			}
			int cursorY = cursorLocation.y * textHeight;

			if (timer + 500 < System.currentTimeMillis()) {
				show = !show;
				timer = System.currentTimeMillis();
			}
			if (show && focused) {
				g.setColor(Color.BLACK);
				g.fillRect(textX + cursorX, textY + cursorY, 2, g.getFontMetrics(font).getHeight());
			}
		}
	}

	/**
	 * provides backwards checking from the flatCursorPosition (which considers the
	 * size of the text as a 1D array) to the cursorLocation Point, which considers
	 * the text size as a 2D array. This is particularly important when shortening
	 * words at the beginning and end of sentences which are in danger of being
	 * wrapped onto a different line. Without this method, the cursor will stay at
	 * it's 2D location, despite the 1D location changing.
	 * 
	 * @param lines: the 2D array of text
	 */
	private void normaliseXandY(String lines[]) {

		int y = 0;
		int x = 0;
		int total = 0;

		// check each line to see if it contains the flatCursorPosition. If it doesn't,
		// increase the cursorLocation.y variable and continue. If it does, set the
		// cursorLocation.x variable to the left over text

		if (cursorLocation.x == 0) {
			// System.out.println();
		}

		for (String l : lines) {
			if (l != null && l.length() > 0) {
				total += l.length();
				if (total < flatCursorPosition) {
					y++;
				} else {
					if (cursorLocation.x == 0 && lines.length > 3 && cursorLocation.y > 0) {
						y++;
						x = 0;
					} else {
						x = l.length() - (total - flatCursorPosition);
					}
					break;
				}
			}
		}

		// update the 2D location from the 1D location
		cursorLocation.x = x;
		cursorLocation.y = y;

	}

	/**
	 * 
	 * searches the whole text displayed and checks if each character is within the
	 * passed mouse click. If it finds a location, set the x and y cursor Location
	 * to that point
	 * 
	 * @param e the mouse location
	 */
	private void findXandYfromMouseLocation(MouseEvent e) {

		int height = g.getFontMetrics(font).getHeight() + 2;

		for (int i = 0; i < lines.length; i++) {
			int totalWidth = 0;
			if (lines[i] != null) {
				if (new Rectangle(textX, textY + (height * i), getBounds().width, height).contains(e.getPoint())) {
					for (int j = 0; j < lines[i].length(); j++) {

						int width = g.getFontMetrics().stringWidth(String.valueOf(lines[i].charAt(j)));

						Rectangle rect = new Rectangle(textX + totalWidth, textY + (height * i), width, height);
						if (rect.contains(e.getPoint())) {
							cursorLocation.y = i;
							cursorLocation.x = j;
							backwardsCheckCursor = true;
							return;
						}
						totalWidth += width;
					}
					// if the x location isn't found then the user has clicked outside of the width
					// bounds. Set the x location to the maximum string length
					cursorLocation.y = i;
					cursorLocation.x = lines[i].length() - 1;
					backwardsCheckCursor = true;
					return;
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {

		// the graphics variable is necessary for finding the width and height values of
		// each line of text from the getXandYfromMouseLocation method
		this.g = g;

		Rectangle clipBounds = g.getClipBounds();
		g.setClip(getBounds());

		g.setColor(shadow);
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		g.setColor(background);
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		wrapString(g);

		g.setColor(boarder);
		g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

		g.setClip(clipBounds);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		// check to see if the user has clicked within the bounds of the TextArea. if
		// they have, set the focused variable to true. This is to ensure that not all
		// text areas in the frame are updated at the same time with the users key
		// presses
		if (getBounds().contains(arg0.getPoint())) {
			focused = true;
			// half the timer value so that it shows immediately
			timer /= 2;
		} else {
			// if the user clicks outside of the area, don't update the values
			focused = false;
		}

		// find the user click location and set the x and y cusor values to that
		// location.
		findXandYfromMouseLocation(arg0);

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		// if the TextArea is focused, update the text values from the user input
		if (focused) {
			StringBuilder sb = new StringBuilder(protectedText);
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (cursorLocation.y > 0) {
					cursorLocation.y--;
				}
				if (cursorLocation.y == 0 && lines[0].equals(" ")) {
					cursorLocation.x = 0;
				}
				if (cursorLocation.x > lines[cursorLocation.y].length()) {
					cursorLocation.x = lines[cursorLocation.y].length() - 1;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_DOWN:
				if (lines[cursorLocation.y + 1] != null) {
					cursorLocation.y++;
				} else {		
					break;
				}
				if (cursorLocation.x > lines[cursorLocation.y].length()) {
					cursorLocation.x = lines[cursorLocation.y].length() - 1;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_LEFT:
				if (flatCursorPosition > 0) {
					cursorLocation.x--;
				}
				if (lines[cursorLocation.y].startsWith("\n") && cursorLocation.x == 0) {
					cursorLocation.x--;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_RIGHT:
				if (flatCursorPosition < protectedText.length() - 1) {
					flatCursorPosition++;
				}
				break;
			case KeyEvent.VK_SHIFT:
				break;
			case KeyEvent.VK_CAPS_LOCK:
				break;
			case KeyEvent.VK_CONTROL:
				break;
			case KeyEvent.VK_END:
				cursorLocation.x = lines[cursorLocation.y].length() - 1;
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_HOME:
				cursorLocation.x = 0;
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_BACK_SPACE:
				if (cursorLocation.x > 0) {
					sb.deleteCharAt(flatCursorPosition - 1);
					cursorLocation.x--;
					flatCursorPosition--;
				} else if (cursorLocation.y > 0) {
					cursorLocation.x--;
					flatCursorPosition--;
				}
				if (lines[cursorLocation.y] != null && lines[cursorLocation.y].startsWith("\n")
						&& cursorLocation.x == 0) {
					sb.deleteCharAt(flatCursorPosition - 1);
					cursorLocation.x--;
					flatCursorPosition--;
				}
				break;
			case KeyEvent.VK_DELETE:
				if (flatCursorPosition < protectedText.length()) {
					sb.deleteCharAt(flatCursorPosition);
					if (cursorLocation.x == lines[cursorLocation.y].length() - 1) {
						sb.deleteCharAt(flatCursorPosition);
					}
					if (lines[cursorLocation.y].equals(" ") || lines[cursorLocation.y].equals("\n ")) {
						sb.deleteCharAt(flatCursorPosition);
						sb.deleteCharAt(flatCursorPosition);
					}
				}
				break;
			case KeyEvent.VK_SPACE:
				if (cursorLocation.x < lines[cursorLocation.y].length()
						|| cursorLocation.y == 0 && cursorLocation.y == lines.length + 3) {
					sb.insert(flatCursorPosition, ("\f"));
					cursorLocation.x++;
					flatCursorPosition++;
				}
				break;
			case KeyEvent.VK_ENTER:
				sb.insert(flatCursorPosition, ("\f\n"));
				flatCursorPosition += 2;
				break;
			default:
				sb.insert(flatCursorPosition, arg0.getKeyChar());
				cursorLocation.x++;
				backwardsCheckCursor = true;
				break;
			}

			setText(sb.toString());

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}

}
