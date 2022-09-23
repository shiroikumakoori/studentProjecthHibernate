import { SafeUrl } from "@angular/platform-browser";
import { Observable } from "rxjs";

export class Post {


    public id!:String;
    public title!:String;
    public message!:String;
    public lastName!:String;
	public firstName!:String;
    public userId!:String;
    public fileId!:String;
    public fileByte!:any;
  
    public imageUrl!:string;
    public safeUrl!:SafeUrl;

}
