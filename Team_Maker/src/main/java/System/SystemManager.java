package System;

import java.awt.*;
import java.util.ArrayList;

import static Main.ChannelManager.*;
import static System.Variable.*;
import static System.EmbedManager.inventoryEmbed;

public class SystemManager {
    /*---------------------------/실행 메서드'!내전'/---------------------------*/
    //!선수등록
    public static void addPlayer(){
        if(!Members.contains(member)){
            if(isRightLine(Message[2])&&isRightLine(Message[3])){
                if(Player.size()<10){
                    Player.add(user.getAsMention()+" "+Message[2]+" "+Message[3]);
                    Members.add(member);
                    SendMessageWithMention("등록이 완료되었습니다.");
                }else{
                    SendMessageWithMention("이미 10명의 선수가 모두 등록했습니다.");
                }
            }else {
                SendMessageWithMention("올바른 라인을 입력하세요\n[탑, 정글, 미드, 원딜, 서폿]");
            }

        }else{
            SendMessageWithMention("이미 등록 되었습니다.");
        }
    }
    //!선수등록취소
    public static void removePlayer(){
        if(Members.contains(member)){
            int index = Members.indexOf(member);
            Player.remove(index);
            Members.remove(index);
            SendMessageWithMention("등록이 취소되었습니다.");
        }else{
            SendMessageWithMention("등록되지 않았습니다.");
        }
    }
    //!등록현황
    public static void getinventory(){
        inventoryEmbed.clear();
        inventoryEmbed.setTitle("내전 선수등록 현황");
        inventoryEmbed.setColor(Color.cyan);
        StringBuilder sb = new StringBuilder();
        for (String s : Player) {
            String[] strings = s.split(" ");
            sb.append(strings[0]);
            sb.append("\n");
        }
        inventoryEmbed.addField("등록자", sb.toString(),true);
        textChannel.sendMessage(inventoryEmbed.build()).queue();
    }
    //!시작
    public static void Start(){
        if(!isGameStart){
            if(isTeamReady){
                CreateVoiceChannel();
                isGameStart = true;
            }else{
                SendMessageWithMention("팀이 생성되지 않았습니다\n!내전 팀생성");
            }
        }else{
            SendMessageWithMention("이미 시작되었습니다.");
        }
    }
    //!종료
    public static void End(){
        if(isGameStart){
            deleteVoiceChannel();
            clear();
            isGameStart = false;
        }else {
            textChannel.sendMessage("아직 시작되지 않았습니다\n!내전 시작").queue();
        }
    }
    /*---------------------------/실행 메서드'!봇'/---------------------------*/
    //!봇 실행
    public static void botStart(){
        if(!isSystemOn){
            isSystemOn = true;
            createMainChannel();
            System.out.println("봇 채널 생성");
        }else{
            SendMessageWithMention("이미 봇이 실행되었거나 다른 서버에서 사용중입니다.");
        }
    }
    //!봇 종료
    public static void botEnd(){
        if (isSystemOn) {
            if(isGameStart){
                SendMessageWithMention("게임이 진행중입니다. 게임을 종료해주세요\n!내전 종료");
            }else{
                isSystemOn = false;
                deleteMainTextChannel();
                deleteMainChannel();
                System.out.println("봇 채널 제거");
            }
        } else {
            textChannel.sendMessage("봇이 실행되지 않았습니다\n !봇 실행").queue();
        }
    }
    /*---------------------------/지원 메서드/---------------------------*/
    public static void sendHelpMessage(){ SendMessageWithMention("!내전 도움말"); }
    public static boolean isRightLine(String string){ return string.equalsIgnoreCase("탑")||string.equalsIgnoreCase("미드")||string.equalsIgnoreCase("정글")||string.equalsIgnoreCase("원딜")||string.equalsIgnoreCase("서폿"); }
    public static void SendMessageWithMention(String text){textChannel.sendMessage(user.getAsMention()+", "+text).queue();}
    public static void SendMessagetoMain(String text){MaintextChannel.sendMessage(text).queue();}
    public static boolean isexistonList(String string){ return topList.contains(string)||jgList.contains(string)||midList.contains(string)||botList.contains(string)||sptList.contains(string); }
    public static void clear(){ Player.clear(); Members.clear(); reset(); isTeamReady=false;isGameStart=false;}

    /*---------------------------/시스템 메서드/---------------------------*/
    public static String[] subText(String s){
        String[] strings;
        strings = s.split(" ");
        return strings;
    }
    public static void printLog(){ System.out.print(guild.getName()+". "+textChannel.getName()+". "+user.getName()+"'"+ message.getContentRaw()+"'\n"); }

    public static void reset(){
        topList = new ArrayList<>();
        tl = new ArrayList<>();
        jgList = new ArrayList<>();
        jl = new ArrayList<>();
        midList = new ArrayList<>();
        ml = new ArrayList<>();
        botList = new ArrayList<>();
        bl = new ArrayList<>();
        sptList = new ArrayList<>();
        sl = new ArrayList<>();
        others = new ArrayList<>();
    }
}
