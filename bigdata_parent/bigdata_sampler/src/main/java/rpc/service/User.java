package rpc.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/12/11.
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String name;

}
