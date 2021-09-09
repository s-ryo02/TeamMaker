package Main;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static System.Variable.*;
import static Main.ChannelManager.*;
import static System.TeamMaker.*;
import static System.SystemManager.*;

public class Main extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        guild = event.getGuild();
        user = event.getAuthor();
        member = event.getMember();
        textChannel = event.getChannel();
        message = event.getMessage();
        Message = subText(event.getMessage().getContentRaw());
        length = Message.length;

        deleteChannel();
        if(user.isBot()) return;
        printLog();

        if(Message[0].equalsIgnoreCase("!봇")){
            if (length == 2) {
                switch(Message[1]){
                    case "실행" -> botStart();
                    case "종료" -> botEnd();
                    case "채널삭제" -> deleteChannel();
                    default -> SendMessageWithMention("!봇 실행\n!봇 종료");
                }
            } else {
                SendMessageWithMention("!봇 실행\n!봇 종료");
            }
        }
        if (Message[0].equalsIgnoreCase("!내전")) {
            if(!isSystemOn) {
                SendMessageWithMention("봇이 실행되지 않았습니다\n!봇 실행");
                return;
            }
            if(!textChannel.getId().equalsIgnoreCase(MaintextChannelID)){
                SendMessageWithMention("["+MaintextChannel.getName() + "]에서 입력해주세요");
                return;
            }
            switch (length) {
                case 2:
                    switch (Message[1]) {
                        case "도움말" -> SendMessageWithMention("""
                                
                                !내전 선수등록 <주라인> <부라인>
                                !내전 선수등록취소
                                !내전 등록현황
                                !내전 팀생성
                                !내전 초기화""");
                        case "선수등록취소" -> removePlayer();
                        case "등록현황" -> getinventory();
                        case "팀생성" -> getTeam();
                        case "초기화" -> clear();
                        case "시작" -> Start();
                        case "종료" -> End();
                        default -> sendHelpMessage();
                    }
                    break;
                case 4:
                    if ("선수등록".equals(Message[1])) {
                        addPlayer();
                    } else {
                        sendHelpMessage();
                    }
                    break;
                default:
                    sendHelpMessage();
                    break;
            }
        }
    }
}
