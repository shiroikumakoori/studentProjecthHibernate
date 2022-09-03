package org.dxc.ManyToMany.Entity;

import java.util.List;

import javax.persistence.*;

@Entity  
@Table(name="ques1123")  
public class QuestionMany {    
    @Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
	private int id;    
	private String qname;   
  
	@ManyToMany(targetEntity = AnswerMany.class, cascade = { CascadeType.ALL })  
	@JoinTable(name = "q_ans1123",   
        joinColumns = { @JoinColumn(name = "q_id") },   
        inverseJoinColumns = { @JoinColumn(name = "ans_id") })  
	private List<AnswerMany> answers;  
  
public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}  
public String getQname() {  
    return qname;  
}  
public void setQname(String qname) {  
    this.qname = qname;  
}  
public List<AnswerMany> getAnswers() {  
    return answers;  
}  
public void setAnswers(List<AnswerMany> answers) {  
    this.answers = answers;  
}      
}  