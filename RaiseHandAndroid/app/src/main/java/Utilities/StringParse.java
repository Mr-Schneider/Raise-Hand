package Utilities;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sae1 on 11/3/17 to clean up the code and
 * make it so that all of the string parsing is handled in this
 * file instead of making all of the classes messy
 */

public class StringParse {

    /* Method that is called within the Utilities -> Classes class
     * when Volley is used to find all of the topics of a class
     */
    public static ArrayList<Topics> parseTopicsVolley(String phpResponse){
            ArrayList<Topics> topics=new ArrayList<>();
            String[] seperated=phpResponse.split(" ");
            int max=seperated.length;
            int i=0;
            //The string can contain multiple parts to indicate when we start reading new information
            while(i<max) {
                if(i<max && seperated[i].equals("NEWTOPIC")) {
                    //NEWTOPIC indicates the start of a new topic, make a new topic object
                    Topics tempTopic= new Topics();
                    ArrayList<Question> q= new ArrayList<Question>();
                    tempTopic.setQuestions(q);
                    i++;
                    while(i<max && !(seperated[i].equals("NEWTOPIC"))) {
                        if(i<max && seperated[i].equals("CREATETIME")){
                            i++;
                            String Time="";
                            while(!(seperated[i].equals("TOPICNAME")) && i<max){
                                //I'm not sure if we need to add a space here or not
                                Time=Time+" "+seperated[i];
                                i++;
                            }
                            tempTopic.setTime(Time);
                        }
                        if(i<max && seperated[i].equals("TOPICNAME")){
                            i++;
                            String Topic="";
                            while(i<max && !(seperated[i].equals("ID"))){
                                Topic=Topic+" "+seperated[i];
                                i++;
                            }
                            tempTopic.setTitle(Topic);
                        }
                        if(i<max && seperated[i].equals("ID")){
                            //id of the topics
                            i++;
                            if(seperated[i].equals("DESCRIPTION")){
                                //do nothing
                            }
                            else {
                                tempTopic.setID(seperated[i]);
                                i++;
                            }
                        }
                        if(i<max && seperated[i].equals("DESCRIPTION")){
                            i++;
                            String Description="";
                            while(i<max && !(seperated[i].equals("NEWQUESTION")) && !(seperated[i].equals("NEWTOPIC"))){
                                Description=Description+" "+seperated[i];
                                i++;
                            }
                            tempTopic.setDescription(Description);
                        }
                        //TODO: Figure out what this isn't working
                        if(i<max && seperated[i].equals("NEWQUESTION")) {
                            //NEWQUESTION means the start of the new question within this topic, add to array list
                            Question tempQuestion= new Question();
                            ArrayList<Reply> replies= new ArrayList<Reply>();
                            tempQuestion.setReplies(replies);
                            i++;
                            //cannot be a new topic or new question starting (maybe need to add in new reply too)?
                            while(i<max && !(seperated[i].equals("NEWTOPIC")) && !(seperated[i].equals("NEWQUESTION"))){
                                //Add new question to the array list for the topic
                                if(i<max && seperated[i].equals("QUESTIONTITLE")){
                                    //header for question
                                    i++;
                                    String title="";
                                    while(i<max && !(seperated[i].equals("QUESTIONDESCRIPTION"))){
                                        title=title+seperated[i]+ " ";
                                        i++;
                                    }
                                    tempQuestion.setQuestionTitle(title);

                                }
                                if(i<max && seperated[i].equals("QUESTIONDESCRIPTION")){
                                    //question
                                    i++;
                                    String desc="";
                                    while(i<max && !(seperated[i].equals("QUESTIONUSER"))){
                                        desc=desc+seperated[i]+ " ";
                                        i++;
                                    }
                                    tempQuestion.setQuestionDescription(desc);

                                }
                                if(i<max && seperated[i].equals("QUESTIONUSER")){
                                    //username who created it
                                    i++;
                                    if(seperated[i].equals("QUESTIONUSERID")) {
                                        //do nothing
                                    }
                                    else{
                                        tempQuestion.setQuestionUsername(seperated[i]);
                                        i++;
                                    }
                                }
                                if(i<max && seperated[i].equals("QUESTIONUSERID")){
                                    //user id who created it
                                    i++;
                                    if(seperated[i].equals("POINTS")){
                                        //do nothing
                                    }
                                    else {
                                        tempQuestion.setOwnerID(seperated[i]);
                                        i++;
                                    }
                                }
                                if(i<max && seperated[i].equals("POINTS")){
                                    //upvotes
                                    i++;
                                    if(seperated[i].equals("ENDORSED")){
                                        //do nothing
                                    }
                                    else {
                                        tempQuestion.setStudentRating(seperated[i]);
                                        i++;
                                    }
                                }
                                if(i<max && seperated[i].equals("ENDORSED")){
                                    //if it is endorsed or not
                                    i++;
                                    if(seperated[i].equals("Yes")){
                                        //this question is endorsed
                                        tempQuestion.setEndorsed(true);
                                        i++;
                                    }
                                    else if(seperated[i].equals("No")){
                                        i++;
                                    }
                                    else{
                                        //do nothing
                                    }
                                }
                                if(i<max && seperated[i].equals("CREATION")){
                                    //timestamp
                                    i++;
                                    String time="";
                                    while(i<max && !(seperated[i].equals("QUESTIONID"))){
                                        time=time+seperated[i]+ " ";
                                        i++;
                                    }
                                    tempQuestion.setCreationTime(time);

                                }
                                if(i<max && seperated[i].equals("QUESTIONID")){
                                    //ID for the question
                                    i++;
                                    tempQuestion.setQuestionID(seperated[i]);
                                    i++;
                                }
                                if(i<max && seperated[i].equals("NEWREPLY")) {
                                    //Get all of the replies
                                    Reply tempR=new Reply();
                                    i++;
                                    while(i<max && !(seperated[i].equals("NEWREPLY")) && !(seperated[i].equals("NEWTOPIC")) && !(seperated[i].equals("NEWQUESTION"))){
                                        //Build a new reply
                                        if(i<max && seperated[i].equals("REPLYTXT")){
                                            i++;
                                            String reply="";
                                            while(i<max && !(seperated[i].equals("REPLYUSER"))){
                                                reply=reply+seperated[i]+" ";
                                                i++;
                                            }
                                            tempR.setReply(reply);

                                        }
                                        if(i<max && seperated[i].equals("REPLYUSER")){
                                            //username of author
                                            i++;
                                            if(seperated[i].equals("REPLYUSERID")){
                                                //do nothing
                                            }
                                            else {
                                                tempR.setReplyUsername(seperated[i]);
                                                i++;
                                            }

                                        }
                                        if(i<max && seperated[i].equals("REPLYUSERID")){
                                            //id of user
                                            i++;
                                            if(seperated[i].equals("POINTS")) {
                                                //do nothing
                                            }
                                            else{
                                                tempR.setReplyUserID(seperated[i]);
                                                i++;
                                            }
                                        }
                                        if(i<max && seperated[i].equals("POINTS")){
                                            i++;
                                            if(seperated[i].equals("ENDORSED")){
                                                //do nothing
                                            }
                                            else {
                                                tempR.setReplyRating(seperated[i]);
                                                i++;
                                            }
                                        }
                                        if(i<max && seperated[i].equals("ENDORSED")){
                                            i++;
                                            if(seperated[i].equals("Yes")){
                                                tempR.setReplyEndorsed(true);
                                                i++;
                                            }
                                            else if(seperated[i].equals("No")){
                                                i++;
                                            }
                                            else{
                                                //do nothing
                                            }
                                        }
                                        if(i<max && seperated[i].equals("CREATION")){
                                            //timestamp
                                            i++;
                                            String time="";
                                            while(i<max && !(seperated[i].equals("PARENT"))){
                                                time=time+seperated[i]+ " ";
                                                i++;
                                            }
                                            tempR.setReplyTimestamp(time);
                                        }
                                        if(i<max && seperated[i].equals("PARENT")){
                                            //timestamp
                                            i++;
                                            String replyParent=seperated[i];
                                            i++;
                                            if(replyParent.equals("0")){
                                                //this is not a reply but a place holder
                                                replyParent=null;
                                            }
                                            tempR.setReplyParent(replyParent);
                                        }
                                        if(i<max && seperated[i].equals("REPLYID")) {
                                            i++;
                                            String replyID=seperated[i];
                                            i++;
                                            tempR.setReplyID(replyID);
                                        }

                                    }
                                    //NEWREPLY means the start of a new reply within this question, add to the question's array list
                                    replies.add(tempR);
                                }
                            }
                            q.add(tempQuestion);
                        }

                    }
                    topics.add(tempTopic);
                }
            }
        return topics;
    }

    public static User parseUserVolley(String phpResponse){

        String[] seperated=phpResponse.split(":");

        if(seperated[1].contains("true")) {
            //concat strings to make it so that the array is properly read from the php response
            String reset = seperated[2];
            reset = reset.substring(1, reset.indexOf(",") - 1);
            String unique_id = seperated[3];
            unique_id = unique_id.substring(1, unique_id.indexOf(",") - 1);
            String roleID = seperated[4];
            roleID = roleID.substring(1, roleID.indexOf(",") - 1);
            String usern = seperated[5];
            usern = usern.substring(1, usern.indexOf(",") - 1);
            String first = seperated[6];
            first = first.substring(1, first.indexOf(",") - 1);
            String last = seperated[7];
            last = last.substring(1, last.indexOf(",") - 1);
            String class_ids = seperated[8];
            ArrayList<Classes> classes = new ArrayList<Classes>();
            class_ids = class_ids.substring(1, class_ids.indexOf("}"));
            Scanner s = new Scanner(class_ids);
            s.useDelimiter(",");
            while (s.hasNext()) {
                String id = s.next();
                id = id.trim();
                if (id.equals("0")) {
                    //do nothing, this is not a class, just a place holder
                } else {
                    Classes c = new Classes("Class", id);
                    classes.add(c);
                }
            }
            User temp= new User(reset,unique_id,roleID,usern,first,last,classes,true);
            return temp;
        }
       return null;
    }
}