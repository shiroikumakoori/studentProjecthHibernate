import { SafeUrl } from "@angular/platform-browser";
import { Observable } from "rxjs";

export class Post {


    public id!:String;
    public views!:number;
    public title!:String;
    public message!:String;
    public lastName!:String;
	public firstName!:String;
    public userId!:String;
    public fileId!:String;
    public fileByte!:any;
    public fileType!:String;
    public urlEmbed!:string;
    public lastModifiedDate!:Date;
	
	public createdDate!:Date;

}
