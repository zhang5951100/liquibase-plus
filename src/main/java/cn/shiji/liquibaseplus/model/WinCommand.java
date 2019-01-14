package cn.shiji.liquibaseplus.model;

import lombok.Data;

/**
 * @author: Eric
 * @date: 2019-01-11 18:53
 **/
@Data
public class WinCommand {

    public String changeLogFile;

    public String driver;

    public String classpath;

    public String url;

    public String username;

    public String password;

    public String command;

    public String parameter;

    private static WinCommand instance = new WinCommand();

    private WinCommand() {
    }


    public static WinCommand getInstance() {
        return instance;
    }
}
