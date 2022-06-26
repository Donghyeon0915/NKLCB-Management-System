/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 *
 * @author Donghyeon <20183188>
 */
@Getter
@Setter
@ToString
public class Group {
    private String groupLink;
    private String groupName;
    private String groupMaster;
    private int members;
}
