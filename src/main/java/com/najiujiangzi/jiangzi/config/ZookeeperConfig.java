package com.najiujiangzi.jiangzi.config;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZookeeperConfig {

    private static ZooKeeper newZookeeper() {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("192.168.2.131:2181", 2000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("执行-->" + watchedEvent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }

    private static void create() {
        ZooKeeper zooKeeper = newZookeeper();
        //节点名；节点值；节点权限（ZooDefs.Ids.OPEN_ACL_UNSAFE为开放所有）；节点类型；
        try {
            zooKeeper.create("/test_2", "testValue02".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void update() {
        ZooKeeper zooKeeper = newZookeeper();
        try {
            zooKeeper.setData("/test_2", "testUpdate".getBytes(), -1);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void getValue() {
        ZooKeeper zooKeeper = newZookeeper();
        try {
            byte[] data = zooKeeper.getData("/test_2", false, null);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void deleted() {
        ZooKeeper zooKeeper = newZookeeper();
        try {
            zooKeeper.delete("/test_2", -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
