package listener;

import domain.LunchVO;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RunchListener extends ListenerAdapter {
	
	private Parser parser = new Parser();
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String msg = event.getMessage().getContentRaw();
		
		if(msg.startsWith("!bs")) {
			//!yy lunch 20191001
			int idx = msg.indexOf(" "); //가장 처음으로 나오는 공백의 index
			if(idx < 0) {
				//event.getChannel().sendMessage("올바른 명령어를 입력하세요").queue();
				sayMsg(event, "올바른 명령어를 입력하세요");
				return;
			}
			//!yy 가 짤려나감.
			String cmd = msg.substring(idx + 1);
			int paramIdx = cmd.indexOf(" ");
			String param = "";
			if(paramIdx >= 0) {
				param = cmd.substring(paramIdx + 1);
				cmd = cmd.substring(0, paramIdx);
			}
			//if문이 끝나면 cmd에는 lunch 가 있고 param에는 20191001이 있게 된다.
			
			switch(cmd) {
			case "echo":
				if(param.isEmpty()) {
					sayMsg(event, "echo명령은 할 말을 입력해야 합니다.");
				}else {
					sayMsg(event, param);
				}
				break;
				
			case "lunch":
				//param이 올바른 값인지 검사하는 로직이 들어가야 해.
				
				LunchVO lunch = parser.getMenu(param);
				sayMsg(event, 
						lunch.getDate() + "의 메뉴는 " + lunch.getMenuString());
				break;
				
			case "help":
				String helpText = "범식이의 명령어는 다음과 같습니다";
				helpText += "!bs echo [하고싶은말] : 다음 말을 따라합니다";
				helpText += "!bs lunch [날짜] : 급식 정보를 알려줍니다";				
				break;
				
			case "pls":
				if(param.isEmpty()) {
					sayMsg(event, "그...그래...! 알려줄게...!\n !bs lunch xxxxx :급식정보를 알려주는거야... \n !bs echo : 이몸이 너의 말을 따라해주는 명령어야..\n 흥 들었으면 이제 꺼져");
				}else {
					sayMsg(event, param);
				}
				break;
			default:
				sayMsg(event, "알 수 없는 명령입니다.");
			}
			
		}
	}
	
	private void sayMsg(MessageReceivedEvent e, String msg) {
		e.getChannel().sendMessage(msg).queue();
	}	
}





