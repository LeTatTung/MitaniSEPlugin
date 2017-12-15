package hut.util.svn.manager;

import hut.util.svn.handler.CommitEventHandler;
import hut.util.svn.handler.InfoHandler;
import hut.util.svn.handler.StatusHandler;
import hut.util.svn.handler.UpdateEventHandler;
import hut.util.svn.handler.WCEventHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.wc.ISVNEventHandler;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;


/**
 * This class is used for managing a working copy. 
 * We can use it to do all the fundamental function
 * of the svn toolkit in java environment
 * @author nxhoaf
 *
 */
public class SvnManagement 
{
	private SVNURL svnRepositoryUrl;
	private String workingCopyPath;
	private SVNClientManager ourClientManager;
	private ISVNEventHandler myCommitEventHandler;
    private ISVNEventHandler myUpdateEventHandler;
    private ISVNEventHandler myWCEventHandler;

	public SVNURL getSvnRepositoryUrl() {
		return svnRepositoryUrl;
	}
	public void setSvnRepositoryUrl(SVNURL svnRepositoryUrl) {
		this.svnRepositoryUrl = svnRepositoryUrl;
	}
	public String getWorkingCopyPath() {
		return workingCopyPath;
	}
	public void setWorkingCopyPath(String workingCopyPath) {
		this.workingCopyPath = workingCopyPath;
	}
	public SVNClientManager getOurClientManager() {
		return ourClientManager;
	}
	public void setOurClientManager(SVNClientManager ourClientManager) {
		this.ourClientManager = ourClientManager;
	}
	public ISVNEventHandler getMyCommitEventHandler() {
		return myCommitEventHandler;
	}
	public void setMyCommitEventHandler(ISVNEventHandler myCommitEventHandler) {
		this.myCommitEventHandler = myCommitEventHandler;
	}
	public ISVNEventHandler getMyUpdateEventHandler() {
		return myUpdateEventHandler;
	}
	public void setMyUpdateEventHandler(ISVNEventHandler myUpdateEventHandler) {
		this.myUpdateEventHandler = myUpdateEventHandler;
	}
	public ISVNEventHandler getMyWCEventHandler() {
		return myWCEventHandler;
	}
	public void setMyWCEventHandler(ISVNEventHandler myWCEventHandler) {
		this.myWCEventHandler = myWCEventHandler;
	}
	/**
	 * create a new SvnManagement object
	 * @param svnRepositoryUrl - the svnRepositoryUrl that you are working with
	 * @param workingCopyPath - the working copy path in your local machine
	 */
	public SvnManagement(String svnRepositoryUrl,String workingCopyPath)
	{
		// initializing library sothat we can access remote host
		this.setupLibrary();
		this.svnRepositoryUrl = null;
		try 
		{
			this.svnRepositoryUrl = SVNURL.parseURIEncoded(svnRepositoryUrl);
			System.out.println("svnRepositoryUrl has been created successfully!");
		}
		catch (SVNException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("you've provided an wrong url:" + e.getMessage());	
			System.exit(1);
		}
		this.workingCopyPath = workingCopyPath;
		
		/*
         * Creating custom handlers that will process events
         */
        myCommitEventHandler = new CommitEventHandler();
        
        myUpdateEventHandler = new UpdateEventHandler();
        
        myWCEventHandler = new WCEventHandler();
        
        // initial the client manager
        ourClientManager = SVNClientManager.newInstance
        	(SVNWCUtil.createDefaultOptions(true));
        /*
         * Sets a custom event handler for operations of an SVNCommitClient 
         * instance
         */
        ourClientManager.getCommitClient().setEventHandler(myCommitEventHandler);
        
        /*
         * Sets a custom event handler for operations of an SVNUpdateClient 
         * instance
         */
        ourClientManager.getUpdateClient().setEventHandler(myUpdateEventHandler);

        /*
         * Sets a custom event handler for operations of an SVNWCClient 
         * instance
         */
        ourClientManager.getWCClient().setEventHandler(myWCEventHandler);
	}
	/**
	 * creates a local directory where the working copy will be checked out into
	 * @param workingCopyPath - the path where we will create a new working copy
	 */
	public void createNewWorkingCopy(String workingCopyPath)
	{
        File wcDir = new File(workingCopyPath);
        if (wcDir.exists()) {
            error("the destination directory '"
                    + wcDir.getAbsolutePath() + "' already exists!", null);
        }
        wcDir.mkdirs();
        System.out.println("The new working copy : '" + wcDir.getAbsolutePath() + "' is created successfully");
	}
	/**
	 * Creates a new version controlled directory (doesn't create any intermediate
     * directories) right in a repository. Like 'svn mkdir URL -m "some comment"' 
     * command
	 * @param url - the url where we will make an directory
	 * @param commitMessage - the message which describe the make directory process 
	 * @return SVNCommitInfo containing information on the new revision committed (revision number, etc.) 
	 */
	public SVNCommitInfo makeDirectory(String url, String commitMessage)
	{
		long committedRevision = -1;
		SVNCommitInfo svnCommitInfo = null;
		SVNURL svnUrl = null;
		
		try 
		{
			svnUrl = svnRepositoryUrl.appendPath(url, false);
		} catch (SVNException e) 
		{
			// TODO Auto-generated catch block
			error("you've provided an wrong url:" + e.getMessage(),e);	
		}
        System.out.println("Making a new directory at '" + svnUrl + "'...");
        try
        {
            /*
             * creates a new version comtrolled directory in a repository and 
             * displays what revision the repository was committed to
             */          
            svnCommitInfo = ourClientManager.getCommitClient().doMkDir(new SVNURL[]{svnUrl}, commitMessage);
            committedRevision = svnCommitInfo.getNewRevision();
        }
        catch(SVNException svne)
        {
            error("error while making a new directory at '" + url + "'", svne);
        }
        System.out.println("The directory " + url + " is made successfully !");
        System.out.println();
        System.out.println("Committed to revision " + committedRevision);
        System.out.println();
        return svnCommitInfo;
    }
	/**
	 * This method does not relate to SVNKit API. Just a method which creates
     * local directories and files 
	 * @param importDir - a derectory which we will create
	 * @param importFile - array of related file(s) which we will create
	 * @param fileContents - the initial contents array which added to these files above
	 */
	public void createLocalDir(String importDir, String[] importFile, String[] fileContents)
    {
		//importDir = workingCopyPath.concat(importDir);
		File aNewDir = new File(importDir);
        if (!aNewDir.mkdirs()) 
        {
            error("failed to create a new directory '" + aNewDir.getAbsolutePath() + "'.", null);
        }
        System.out.println("the directory " + aNewDir.getAbsolutePath() + " was created sucessfully");
        for(int i=0; i < importFile.length; i++)
        {
        	//String filePath = workingCopyPath.concat(importFile[i]);
	        //File aNewFile = new File(filePath);
        	File aNewFile = new File(importFile[i]);
            try 
            {
	            if (!aNewFile.createNewFile()) 
	            {
	                error("failed to create a new file '"
	                        + aNewFile.getAbsolutePath() + "'.", null);
	            }
	            System.out.println("the file " + aNewFile.getAbsolutePath() + " was created sucessfully");
	        } 
            catch (IOException ioe)
            {
	            aNewFile.delete();
	            error("error while creating a new file '"
	                    + aNewFile.getAbsolutePath() + "'", ioe);
	        }
	
	        String contents = null;
	        if(i > fileContents.length-1)
	        {
	            continue;
	        }
            contents = fileContents[i];
	        
	        /*
	         * writing a text into the file
	         */
	        FileOutputStream fos = null;
	        try 
	        {
	            fos = new FileOutputStream(aNewFile);
	            fos.write(contents.getBytes());
	        } 
	        catch (FileNotFoundException fnfe) 
	        {
	            error("the file '" + aNewFile.getAbsolutePath() + "' is not found", fnfe);
	        }
	        catch (IOException ioe) 
	        {
	            error("error while writing into the file '"
	                    + aNewFile.getAbsolutePath() + "'", ioe);
	        }
	        finally 
	        {
	            if (fos != null) 
	            {
	                try 
	                {
	                    fos.close();
	                } 
	                catch (IOException ioe) 
	                {
	                    //
	                }
	            }
	        }
        }
    }
	/**
	 * Collects information on local path(s). Like 'svn info (-R)' command.
	 * @param workingCopyPath - a local entry for which info will be collected;
	 * @param revision - a revision of an entry which info is interested in; if it's not
     * WORKING then info is got from a repository;
	 * @param isRecursive - if true and an entry is a directory then doInfo(..) collects info 
     * not only for that directory but for each item inside stepping down recursively;
	 */
	public void showWorkingCopyInfo(String workingCopyPath, SVNRevision revision, boolean isRecursive)
	{
		File wcPath = new File(workingCopyPath);
		if (!wcPath.exists())
		{
			wcPath.mkdir();
		}
        try 
        {
			ourClientManager.getWCClient().doInfo(wcPath, revision, isRecursive, new InfoHandler());
		} 
        catch (SVNException e) 
        {
			// TODO Auto-generated catch block
			error("error while recursively getting info for the working copy at'"
                    + wcPath.getAbsolutePath() + "'", e);
		}
    }
	/**
	 * Imports an unversioned directory into a repository location denoted by a
     * destination URL (all necessary parent non-existent paths will be created 
     * automatically). This operation commits the repository to a new revision. 
     * Like 'svn import PATH URL (-N) -m "some comment"' command.
     * @param localPath a local unversioned directory or singal file that will be imported into a repository;
     * @param relativeDir a repository location where the local unversioned directory/file will be imported into; this URL path may contain non-existent parent paths that will be created by the repository server;
     * @param commitMessage a commit log message since the new directory/file are immediatelycreated in the repository;
     * @param isRecursive if true and path parameter corresponds to a directory then the directorywill be added with all its child subdirictories, otherwise the operation will coveronly the directory itself (only those files which are located in the directory). 
     * @return Returns SVNCommitInfo containing information on the new revision committed (revision number, etc.) 
	 */
	public SVNCommitInfo importDirectory(String localPath, String relativeDir, String commitMessage, boolean isRecursive)
	{	
		SVNURL importToURL = null ;
		SVNCommitInfo svnCommitInfo = null;
		
		File myLocalPath = new File(localPath);
		try 
		{
			importToURL = svnRepositoryUrl.appendPath(relativeDir, false);
		} catch (SVNException e) 
		{
			// TODO Auto-generated catch block
			error("Cannot import folder to the directory " + relativeDir, e);
		}
		System.out.println("Importing a new directory into '" + importToURL + "'...");
        try 
        {
			svnCommitInfo = ourClientManager.getCommitClient().doImport(myLocalPath, importToURL, commitMessage, isRecursive);
		} catch (SVNException e)
{
			// TODO Auto-generated catch block
			error("error while importing a new directory '" + importToURL + "' into '" + importToURL + "'", e);
		}
		return svnCommitInfo;
        
    }
	/**
	 * Committs changes in a working copy to a repository. Like 
     * 'svn commit PATH -m "some comment"' command.
	 * @param workingCopyPath - working copy paths which changes are to be committed;
	 * @param keepLocks - if true then doCommit(..) won't unlock locked paths; otherwise they will
     * be unlocked after a successful commit;
	 * @param commitMessage - a commit log message;
	 * @return - SVNCommitInfo containing information on the new revision committed 
         * (revision number, etc.) 
	 */
	public SVNCommitInfo commit(String workingCopyPath, boolean keepLocks, String commitMessage)
    {
		File wcPath = new File(workingCopyPath);
		SVNCommitInfo svnCommitInfo = null;
		System.out.println("Committing changes for '" + wcPath.getAbsolutePath() + "'...");
		/*
		 * Returns SVNCommitInfo containing information on the new revision committed 
		 * (revision number, etc.) 
		 */
		try 
		{
			svnCommitInfo = ourClientManager.getCommitClient().doCommit(new File[] { wcPath }, keepLocks,
			        commitMessage, false, true);
		} 
		catch (SVNException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			error("error while committing changes to the working copy at '"
	                + wcPath.getAbsolutePath()
	                + "'", e);
		}   
    	System.out.println("Committed to revision " + svnCommitInfo.getNewRevision());
		return svnCommitInfo;
	}
	/**
	 * Schedules directories and files for deletion from version control upon the next
     * commit (locally). Like 'svn delete PATH' command.
	 * @param entryPath - an entry to be scheduled for deletion;
	 * @param force - a boolean flag which is set to true to force a deletion even if an entry
     * has local modifications;
	 */
    public void delete(String entryPath, boolean force)
    {
    	File wcPath = new File(entryPath);
    	boolean isDryRun = false;
    	System.out.println("Scheduling '" + wcPath.getAbsolutePath() + "' for deletion ...");
        try 
        {
			ourClientManager.getWCClient().doDelete(wcPath, force, isDryRun);
		}
        catch (SVNException e) 
		{
			// TODO Auto-generated catch block
        	error("error while schediling '"
                    + wcPath.getAbsolutePath() + "' for deletion", e);
		}
    }
	/**
	 * Puts directories and files under version control scheduling them for addition 
	 * to a repository. They will be added in a next commit. Like 'svn add PATH'  command
	 * @param anEntryPath - an entry to be scheduled for addition;
	 */
    public void addEntry(String anEntryPath)
    {   
    	File entryPath = new File (anEntryPath);
    	System.out.println("Recursively scheduling a new directory '" + entryPath.getAbsolutePath() + "' for addition...");
    	boolean isForcedAddition = false;
		boolean isMkDir = false;
		boolean isClimbUnversionedParents = false;
		boolean isRecursive = true;
    	try 
        {  		
        	ourClientManager.getWCClient().doAdd(entryPath, isForcedAddition,isMkDir,isClimbUnversionedParents,isRecursive);
        } 
        catch (SVNException svne) 
        {
            error("error while recursively adding the directory '"
                    + entryPath.getAbsolutePath() + "'", svne);
        }      
        System.out.println("the entry " + entryPath.getAbsolutePath() + " was added to the repository sucessfully");
    }
    /**
     * Checks out a working copy from a repository. Like 'svn checkout URL[@REV] PATH (-r..)' command
     * @param url - a repository location from where a working copy is to be checked out;
     * @param revision - a revision at which a working copy being checked out is to be;
     * @param destinationPath - a local path where the working copy will be fetched into;
     * @param isRecursive - if true and url corresponds to a directory then doCheckout(..) recursively fetches out the entire directory, otherwise - only child entries of the directory;
     * @return  - returns the number of the revision at which the working copy is checked out
     */
    public long checkout(String url,SVNRevision revision, String destinationPath, boolean isRecursive)
     {
    	SVNURL svnUrl = null ;
    	File destPath = new File(destinationPath);
    	if (!destPath.exists())
    	{
    		destPath.mkdir();
    	}
    	long checkOutAtRevision = -1;
		try 
		{
			svnUrl = SVNURL.parseURIDecoded(url);
		}
		catch (SVNException e) 
		{
			// TODO Auto-generated catch block
			error("error while parsing : '"
                    + url + "'", e);
		}
        SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
        /*
         * sets externals not to be ignored during the checkout
         */
        updateClient.setIgnoreExternals(false);
        try 
        {
			checkOutAtRevision = updateClient.doCheckout(svnUrl, destPath, revision, revision, isRecursive);
		} 
        catch (SVNException e) 
        {
			// TODO Auto-generated catch block
        	error("error while checking out a working copy for the location '"
                    + url + "'", e);
		}
        System.out.println("Successfully check out from : " + svnUrl.getPath());
        return checkOutAtRevision;
    }
    /**
     * Updates a working copy (brings changes from the repository into the working copy). 
     * Like 'svn update PATH' command
     * @param workingcopyPath - a working copy entry that is to be updated;
     * @param updateToRevision - a revision to which a working copy is to be updated;
     * @param isRecursive - if true and an entry is a directory then doUpdate(..) recursively 
     * updates the entire directory, otherwise - only child entries of the directory;   
     * @return the number of the revision wcPath was updated to
     */
    public long update(String workingcopyPath,SVNRevision updateToRevision, boolean isRecursive)
    {
    	File wcPath = new File(workingcopyPath);
    	long newRevision = -1;
        SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
        /*
         * sets externals not to be ignored during the update
         */
        updateClient.setIgnoreExternals(false);
        /*
         * returns the number of the revision wcPath was updated to
         */
        try 
        {
			newRevision =  updateClient.doUpdate(wcPath, updateToRevision, isRecursive);
		} 
        catch (SVNException e) 
        {
			// TODO Auto-generated catch block
        	error("error while recursively updating the working copy at '"
                    + wcPath.getAbsolutePath() + "'", e);
		}
        System.out.println("Successfully updated to revision : " + updateToRevision);
        return newRevision;
    }
    /**
     * Displays status information for each entry in the console (in the 
     * manner of the native Subversion command line client)
     * @param workingCopyPath - an entry which status info to be gathered;
     * @param isRecursive - if true and an entry is a directory then doStatus(..) collects status 
     * info not only for that directory but for each item inside stepping down recursively;
     * @param isRemote - if true then doStatus(..) will cover the repository (not only the working copy)
     * as well to find out what entries are out of date;
     * @param isReportAll - if true then doStatus(..) will also include unmodified entries;
     * @param isIncludeIgnored - if true then doStatus(..) will also include entries being ignored;
     * @param isCollectParentExternals - if true then externals definitions won't be ignored;
     */
    public void showStatus(String workingCopyPath, boolean isRecursive, boolean isRemote, boolean isReportAll,
            boolean isIncludeIgnored, boolean isCollectParentExternals)
    {
    	File wcPath = new File(workingCopyPath);
    	System.out.println("Status for '" + wcPath.getAbsolutePath() + "':");
        try 
        {
			ourClientManager.getStatusClient().doStatus(wcPath, isRecursive, isRemote, isReportAll,
			        isIncludeIgnored, isCollectParentExternals, new StatusHandler(isRemote));
		} 
        catch (SVNException e) 
        {
			// TODO Auto-generated catch block
        	error("error while recursively performing status for '"
                    + wcPath.getAbsolutePath() + "'", e);
		}
    }
	private void setupLibrary() 
	{
        /*
         * For using over http:// and https://
         */
        DAVRepositoryFactory.setup();
        /*
         * For using over svn:// and svn+xxx://
         */
        SVNRepositoryFactoryImpl.setup();
        
        /*
         * For using over file:///
         */
        FSRepositoryFactory.setup();
    }
	/*
     * Displays error information and exits. 
     */
    private void error(String message, Exception e)
    {
        System.err.println(message+(e!=null ? ": "+e.getMessage() : ""));
        System.exit(1);
    }
}
