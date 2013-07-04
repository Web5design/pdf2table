/*
    Copyright 2005, 2005 Burcu Yildiz
    Contact: burcu.yildiz@gmail.com
    
    This file is part of pdf2table.
*/

package pdf2xml;

import java.util.*;

public class MyNode {

int left;
int right;
int level;
String content;
Vector nodes;
Text_Element text_element;

  public MyNode(String c, int l) {
    // used only for the construction of the root element in second_classification.java
    this.content = c;
	this.level = l;
	this.nodes = new Vector();
  }
  
  public MyNode(Text_Element t , int l) {
    this.text_element = t;
    this.content = t.value;
	this.left = t.left;
	this.right = t.right;
	this.level = l;
	this.nodes = new Vector();
  }
  
  public MyNode(String c, Text_Element t , int l) {
    this.content = c;
	this.left = t.left;
	this.right = t.right;
	this.level = l;
	this.nodes = new Vector();
  }
  
}