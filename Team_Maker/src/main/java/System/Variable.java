package System;

import net.dv8tion.jda.api.entities.*;

import java.util.ArrayList;

public class Variable {
    /*---------------------------/변수 선언/---------------------------*/
    //시스템 변수
    public static boolean isSystemOn = false;
    public static boolean isGameStart = false;
    public static boolean isTeamReady = false;
    //정보 변수
    public static Guild guild;
    public static TextChannel textChannel;
    public static User user;
    public static Member member;
    public static Message message;
    public static String[] Message;
    public static int length;
    //플레이어 변수
    public static ArrayList<String> Player = new ArrayList<>();
    public static ArrayList<Member> Members = new ArrayList<>();
    public static ArrayList<Member> team1 = new ArrayList<>();
    public static ArrayList<Member> team2 = new ArrayList<>();
    //라이너 변수
    public static ArrayList<String> topList = new ArrayList<>();
    public static ArrayList<String> tl = new ArrayList<>();
    public static ArrayList<String> jgList = new ArrayList<>();
    public static ArrayList<String> jl = new ArrayList<>();
    public static ArrayList<String> midList = new ArrayList<>();
    public static ArrayList<String> ml = new ArrayList<>();
    public static ArrayList<String> botList = new ArrayList<>();
    public static ArrayList<String> bl = new ArrayList<>();
    public static ArrayList<String> sptList = new ArrayList<>();
    public static ArrayList<String> sl = new ArrayList<>();
    public static ArrayList<String> others = new ArrayList<>();
}
