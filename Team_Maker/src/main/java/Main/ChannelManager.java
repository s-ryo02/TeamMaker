package Main;

import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static System.Variable.*;
import static System.SystemManager.*;

public class ChannelManager extends ListenerAdapter {
    public static TextChannel MaintextChannel;
    public static String MaintextChannelID;
    static String Textchannelname = "롤내전";
    public static String team1VoiceID;
    public static String team2VoiceID;
    public static String CategoryID;

    public static void CreateVoiceChannel(){
        guild.getCategoryById(CategoryID).createVoiceChannel("1팀").queue();
        guild.getCategoryById(CategoryID).createVoiceChannel("2팀").queue();
    }
    public static void deleteVoiceChannel(){
        if(team1VoiceID !=null&&team2VoiceID!=null){
            guild.getVoiceChannelById(team1VoiceID).delete().queue();
            guild.getVoiceChannelById(team2VoiceID).delete().queue();
        }else{
            System.out.println("보이스채널 삭제 오류");
        }
    }

    @Override
    public void onVoiceChannelCreate(@NotNull VoiceChannelCreateEvent event) {
        VoiceChannel vc = event.getChannel();
        System.out.println("VoiceChannel named [" + vc.getName() +"] have made.");
        if(event.getChannel().getName().equalsIgnoreCase("1팀")){
            team1VoiceID = vc.getId();
            System.out.println("봇 채널 1팀 ID : " + team1VoiceID);
            moveteam1();
        }else if(event.getChannel().getName().equalsIgnoreCase("2팀")){
            team2VoiceID = vc.getId();
            System.out.println("봇 채널 2팀 ID : " + team2VoiceID);
            moveteam2();
        }
    }

    public static void deleteMainTextChannel(){
        if(MaintextChannelID != null){
            guild.getTextChannelById(MaintextChannelID).delete().queue();
        }else{
            System.out.println("채팅채널 삭제 오류");
        }
    }
    @Override
    public void onTextChannelCreate(@NotNull TextChannelCreateEvent event) {
        TextChannel tc = event.getChannel();
        System.out.println("TextChannel named [" + tc.getName() +"] have made.");
        if(event.getChannel().getName().equalsIgnoreCase(Textchannelname)){
            MaintextChannelID = tc.getId();
            System.out.println("봇 채널 ID : " + MaintextChannelID);
            MaintextChannel = tc;
            MaintextChannel.sendMessage(user.getAsMention() + "롤내전 채널이 생성되었습니다.");
        }
    }
    public static void deleteChannel(){
        List<GuildChannel> channellist;
        channellist = guild.getChannels();
        System.out.println(channellist.toString());
        for (GuildChannel guildChannel : channellist) {
            String s = guildChannel.getName();
            if (s.equalsIgnoreCase("롤내전")) {
                guildChannel.delete().queue();
            } else if (s.equalsIgnoreCase("1팀")) {
                guildChannel.delete().queue();
            } else if (s.equalsIgnoreCase("2팀")) {
                guildChannel.delete().queue();
            } else if (s.equalsIgnoreCase("내전 봇")) {
                guildChannel.delete().queue();
            }
        }
    }
    /*---------------------------/채널 이동 매서드/---------------------------*/
    public static void moveteam1(){
        for(int i=0;i<Members.size();i++){
            if(team1.contains(Members.get(i))) {
                try{
                    guild.moveVoiceMember(Members.get(i), guild.getVoiceChannelById(team1VoiceID)).queue();
                    System.out.println("1팀 선수이동");
                }catch (Exception e){
                    SendMessagetoMain(Members.get(i).getUser().getAsMention()+"님이 통화방에 없어 내전 통화방으로 이동하지 못했습니다");
                    System.out.println(e);
                }
            }
        }
    }
    public static void moveteam2(){
        for(int i=0;i<Members.size();i++) {
            if (team2.contains(Members.get(i))) {
                try{
                    guild.moveVoiceMember(Members.get(i), guild.getVoiceChannelById(team2VoiceID)).queue();
                    System.out.println("2팀 선수이동");
                }catch (Exception e){
                    SendMessagetoMain(Members.get(i).getUser().getAsMention()+"님이 통화방에 없어 내전 통화방으로 이동하지 못했습니다");
                    System.out.println(e);
                }
            }
        }
    }
    public static void createMainChannel(){
        guild.createCategory("내전 봇").queue();
    }
    public static void deleteMainChannel(){
        if(CategoryID != null&&MaintextChannelID != null){
            guild.getCategoryById(CategoryID).delete().queue();
        }
    }
    @Override
    public void onCategoryCreate(@NotNull CategoryCreateEvent event) {
        CategoryID = event.getCategory().getId();
        event.getCategory().createTextChannel(Textchannelname).queue();
    }
}
