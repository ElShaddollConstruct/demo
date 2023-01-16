package com.example.demo;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

public abstract class BaseDAO<C> {
    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    final String driver="com.mysql.jdbc.Driver";
    final String url="jdbc:mysql://localhost:3306/fruit?useUnicode=true&useSSL=false&characterEncoding=utf-8";
    final String user="root";
    final String password="root";

    protected Class entityClass;
    public BaseDAO()
    {
        Type genericType = getClass().getGenericSuperclass();
        Type[] actualTypeArg=((ParameterizedType) genericType).getActualTypeArguments();
        Type actualType=actualTypeArg[0];
        try {
            entityClass=Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    protected Connection connect()
    {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException|SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected void close(Connection conn,PreparedStatement psmt,ResultSet rs)
    {
        try {
            if (rs!=null)
            {
                rs.close();
            }
            if (psmt!=null)
            {
                psmt.close();
            }
            if (conn!=null&&!conn.isClosed())
            {
                conn.close();
            }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

    protected void setValue(Object obj,String property,Object propertyValue) throws NoSuchFieldException, IllegalAccessException {
        Class class0=obj.getClass();
        Field field=class0.getDeclaredField(property);
        if (field!=null)
        {
            field.setAccessible(true);
            field.set(obj,propertyValue);
        }
    }

    protected int update(String sql,Object... params)
    {

        try {
            conn=connect();
            psmt=conn.prepareStatement(sql);
            if (params!=null&&params.length>0)
            {
                for (int i=0;i< params.length;i++)
                    psmt.setObject(i+1,params[i]);
            }
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(conn,psmt,rs);
        }
    }

    protected ArrayList<C> selectALL(String sql,Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        ArrayList<C> list=new ArrayList<>();
        conn=connect();
        psmt=conn.prepareStatement(sql);
        if (params!=null&&params.length>0)
        {
            for (int i=0;i< params.length;i++)
                psmt.setObject(i+1,params[i]);
        }
        rs= psmt.executeQuery();
        ResultSetMetaData rsmd=rs.getMetaData();
        int columnCount= rsmd.getColumnCount();
        while (rs.next())
        {
            C entity=(C)entityClass.newInstance();
            for (int i=0;i<columnCount;i++)
            {
                String columnName=rsmd.getColumnName(i+1);
                Object columnValue=rs.getObject(i+1);
                setValue(entity,columnName,columnValue);
            }
            list.add(entity);
        }
        return list;
    }

    protected C select(String sql,Object... params) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        conn=connect();
        psmt=conn.prepareStatement(sql);
        if (params!=null&&params.length>0)
        {
            for (int i=0;i< params.length;i++)
                psmt.setObject(i+1,params[i]);
        }
        rs= psmt.executeQuery();
        ResultSetMetaData rsmd=rs.getMetaData();
        int columnCount= rsmd.getColumnCount();
        if (rs.next())
        {
            C entity=(C)entityClass.newInstance();
            for (int i=0;i<columnCount;i++)
            {
                String columnName=rsmd.getColumnName(i+1);
                Object columnValue=rs.getObject(i+1);
                setValue(entity,columnName,columnValue);
            }
            return entity;
        }
        return null;
    }
}
