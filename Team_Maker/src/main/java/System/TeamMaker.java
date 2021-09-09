package System;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static System.EmbedManager.TeamEmbed;
import static System.Variable.*;
import static System.SystemManager.*;

public class TeamMaker {
    //!팀생성
    public static void getTeam(){
        setTeam();
        TeamEmbed.clear();
        TeamEmbed.setTitle("팀 목록");
        TeamEmbed.setColor(Color.CYAN);
        TeamEmbed.setDescription("----------------------------------------");
        TeamEmbed.addField("라인", "1팀\n2팀", true);
        TeamEmbed.addField("탑", getLiner(topList), true);
        TeamEmbed.addField("정글", getLiner(jgList), true);
        TeamEmbed.addField("미드", getLiner(midList), true);
        TeamEmbed.addField("원딜", getLiner(botList), true);
        TeamEmbed.addField("서폿", getLiner(sptList), true);
        setTeamMembers(topList);
        setTeamMembers(jgList);
        setTeamMembers(midList);
        setTeamMembers(botList);
        setTeamMembers(sptList);
        System.out.println(team1.toString());
        System.out.println(team2.toString());
        textChannel.sendMessage(TeamEmbed.build()).queue();
        isTeamReady = true;
    }
    //1,2팀 배정
    public static void setTeamMembers(ArrayList<String> arrayList){
        for(int i=0;i<Player.size();i++){
            String s= Player.get(i);
            if(arrayList.size()==1){
                if(s.contains(arrayList.get(0))){
                    team1.add(Members.get(i));
                }
            }else if(arrayList.size()==2){
                if(s.contains(arrayList.get(0))){
                    team1.add(Members.get(i));
                }
                if(s.contains(arrayList.get(1))){
                    team2.add(Members.get(i));
                }
            }
        }
    }
    //라이너 정보 문자열로 출력
    public static String getLiner(ArrayList<String> arrayList){
        if(arrayList.size()==0){
            return "\n";
        }else if(arrayList.size()==1){
            return arrayList.get(0)+"\n";
        }else if(arrayList.size()==2){
            return arrayList.get(0)+"\n"+arrayList.get(1);
        }else{
            return "\n";
        }
    }
    //팀 설정
    public static void setTeam(){
        reset();
        //1순위 배정
        for (String s : Player) {
            String[] strings = s.split(" ");
            settemLine(strings[1], strings[0]);
        }
        System.out.println(tl.toString()+jl.toString()+ml.toString()+bl.toString()+sl.toString());
        setLine();
        System.out.println(topList.toString()+jgList.toString()+midList.toString()+botList.toString()+sptList.toString());
        tl.clear();jl.clear();ml.clear();bl.clear();sl.clear();
        //2순위 배정
        for (String s : Player) {
            String[] strings = s.split(" ");
            settemLine(strings[2], strings[0]);
        }
        System.out.println(tl.toString()+jl.toString()+ml.toString()+bl.toString()+sl.toString());
        setLine();
        System.out.println(topList.toString()+jgList.toString()+midList.toString()+botList.toString()+sptList.toString());
        tl.clear();jl.clear();ml.clear();bl.clear();sl.clear();
        for (String s : Player) {
            String[] strings = s.split(" ");
            if (!isexistonList(strings[0])) {
                others.add(strings[0]);
            }
        }
        //남는사람 배정
        Collections.shuffle(others);
        for(int i=0;i<2;i++){
            if(topList.size()<2){
                if(!others.isEmpty()) {
                    topList.add(others.get(0));
                    others.remove(0);
                }
            }if(jgList.size()<2){
                if(!others.isEmpty()) {
                    jgList.add(others.get(0));
                    others.remove(0);
                }
            }if(midList.size()<2){
                if(!others.isEmpty()) {
                    midList.add(others.get(0));
                    others.remove(0);
                }
            }if(botList.size()<2){
                if(!others.isEmpty()) {
                    botList.add(others.get(0));
                    others.remove(0);
                }
            }if(sptList.size()<2){
                if(!others.isEmpty()) {
                    sptList.add(others.get(0));
                    others.remove(0);
                }
            }
        }
        System.out.println(topList+" "+jgList+" "+midList+" "+botList+" "+sptList);
    }
    //임시 라인 설정
    public static void settemLine(String string, String name){
        if(string.equalsIgnoreCase("탑")){
            if(!isexistonList(name)){
                tl.add(name);
            }
        }if(string.equalsIgnoreCase("정글")){
            if(!isexistonList(name)){
                jl.add(name);
            }
        }if(string.equalsIgnoreCase("미드")){
            if(!isexistonList(name)){
                ml.add(name);
            }
        }if(string.equalsIgnoreCase("원딜")){
            if(!isexistonList(name)){
                bl.add(name);
            }
        }if(string.equalsIgnoreCase("서폿")){
            if(!isexistonList(name)){
                sl.add(name);
            }
        }
        Collections.shuffle(tl);Collections.shuffle(jl);Collections.shuffle(ml);Collections.shuffle(bl);Collections.shuffle(sl);
    }
    //라인 배정
    public static void setLine(){
        for(int i =0;i<2;i++){
            if(topList.size()<2){
                if(!tl.isEmpty()) {
                    topList.add(tl.get(0));
                    tl.remove(0);
                }
            }if(jgList.size()<2){
                if(!jl.isEmpty()){
                    jgList.add(jl.get(0));
                    jl.remove(0);
                }
            }if(midList.size()<2){
                if(!ml.isEmpty()) {
                    midList.add(ml.get(0));
                    ml.remove(0);
                }
            }if(botList.size()<2){
                if(!bl.isEmpty()) {
                    botList.add(bl.get(0));
                    bl.remove(0);
                }
            }if(sptList.size()<2){
                if(!sl.isEmpty()) {
                    sptList.add(sl.get(0));
                    sl.remove(0);
                }
            }
        }
    }
}
