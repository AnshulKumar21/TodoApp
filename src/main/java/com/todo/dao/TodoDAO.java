package com.todo.dao;

import com.todo.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public static void addTask(String task) throws Exception {
        String sql = "INSERT INTO todos(task) VALUES (?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, task);
            ps.executeUpdate();
        }
    }

    public static List<String> getAllTasks() throws Exception {
        List<String> tasks = new ArrayList<>();
        String sql = "SELECT task FROM todos";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tasks.add(rs.getString("task"));
            }
        }
        return tasks;
    }
}
