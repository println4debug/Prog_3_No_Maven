/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package ui;

import events.accessfile.implementation.CMDAccessFileEvent;
import events.accessfile.implementation.CMDAccessFileHandler;
import events.accessrandomfile.implementation.CMDAccessRandomFileHandler;
import events.createfile.implementation.CMDCreateFileEvent;
import events.createfile.implementation.CMDCreateFileHandler;
import events.createuploader.implementation.CMDCreateUploaderEvent;
import events.createuploader.implementation.CMDCreateUploaderHandler;
import events.deletefile.implementation.CMDDeleteFileEvent;
import events.deletefile.implementation.CMDDeleteFileHandler;
import events.deleterandomfile.implementation.CMDDeleteRandomFileHandler;
import events.eventinfo.implementation.CMDEventInfoHandler;

import java.util.Scanner;

public class Console {

    private Scanner sc;
    private CMDCreateUploaderHandler createUploadHandler;
    private CMDCreateFileHandler createFileHandler;
    private CMDDeleteFileHandler deleteFileHandler;
    private CMDAccessFileHandler accessHandler;
    private CMDAccessRandomFileHandler accessRandomHandler;
    private CMDEventInfoHandler infoHandler;
    private CMDDeleteRandomFileHandler delRndHandler;

    public Console() {
        this.sc = new Scanner(System.in);
        this.createUploadHandler = new CMDCreateUploaderHandler();
        this.createFileHandler = new CMDCreateFileHandler();
        this.deleteFileHandler = new CMDDeleteFileHandler();
        this.infoHandler = new CMDEventInfoHandler();
        this.accessHandler = new CMDAccessFileHandler();
        this.delRndHandler = new CMDDeleteRandomFileHandler();
        this.accessRandomHandler = new CMDAccessRandomFileHandler();
    }

    public Console(CMDCreateUploaderHandler createUploaderHandler, CMDCreateFileHandler createFileHandler,
                   CMDDeleteFileHandler deleteFileHandler, CMDAccessFileHandler accessHandler) {
        this.sc = new Scanner(System.in);
        this.createUploadHandler = createUploaderHandler;
        this.createFileHandler = createFileHandler;
        this.deleteFileHandler = deleteFileHandler;
        this.accessHandler = accessHandler;
        this.delRndHandler = new CMDDeleteRandomFileHandler();
        this.accessRandomHandler = new CMDAccessRandomFileHandler();
    }

    public String readString() {
        String result = sc.nextLine();
        if(result == null) {throw new NullPointerException("String was null!"); }
        return result;
    }

    public int readNumber() {
        return sc.nextInt();
    }

    public void print(String s) {
        if(s == null) { throw new IllegalArgumentException("String was null!"); }
        System.out.println(s);
    }

    public void readCommand() {
        print("Please enter a command: ");
        String input = sc.nextLine();
        if(input == null) {throw new NullPointerException("String was null!"); }
        String[] args = input.split(" ");

        switch(args[0]) {
            case ":u":
                if(args.length == 2) {
                    CMDCreateUploaderEvent eventUL = new CMDCreateUploaderEvent(this,args[1]);
                    createUploadHandler.handle(eventUL);
                    break;
                } else {
                    print("Invalid Arguments! :u [Uploader name]");
                    break;
                }
            case ":c":
                if(args.length == 3) {
                    CMDCreateFileEvent eventF = new CMDCreateFileEvent(this,args[1],args[2]);
                    createFileHandler.handle(eventF);
                    break;
                } else {
                    print("Invalid Arguments! :c [File type] [Uploader name]");
                    break;
                }
            case ":d":
                if(args.length == 2) {
                    CMDDeleteFileEvent eventD = new CMDDeleteFileEvent(this,args[1]);
                    deleteFileHandler.handle(eventD);
                    break;
                } else {
                    print("Invalid Arguments! :c [File type] [Uploader name]");
                    break;
                }
            case ":a":
                if(args.length == 2) {
                    CMDAccessFileEvent eventA = new CMDAccessFileEvent(this,args[1]);
                    accessHandler.handle(eventA);
                    break;
                } else {
                    print("Invalid Arguments! :c [File type] [Uploader name]");
                    break;
                }
            case ":i":
                print("Available Commands:");
                print("Create Uploader: :u [Uploader name]");
                print("Create File: :c [File type] [Uploader name]");
                print("Delete File: :d [File Address]");
                print("Access File: :a [File Address]");
                print("Available File Types: :ft");
                break;

            case ":ft":
                print("Available Filetypes:");
                print("Audio, Video, AudioVideo, InteractiveVideo, LicensedAudio, LicensedVideo, LicensedAudioVideo");
                break;
            default:
                print("Unknown Command, type :i for a list of commands!");
                break;
        }
    }

    public CMDCreateUploaderHandler getCreateUploadHandler() {
        return this.createUploadHandler;
    }

    public CMDCreateFileHandler getCreateFileHandler() {
        return this.createFileHandler;
    }

    public CMDEventInfoHandler getInfoHandler() {
        return this.infoHandler;
    }

    public CMDAccessFileHandler getAccessHandler() {return this.accessHandler; }

    public CMDDeleteFileHandler getDeleteFileHandler() {
        return this.deleteFileHandler;
    }

    public CMDDeleteRandomFileHandler getDeleteRandomFileHandler() {return this.delRndHandler;}

    public CMDAccessRandomFileHandler getAccessRandomFileHandler() {return this.accessRandomHandler;}
}
