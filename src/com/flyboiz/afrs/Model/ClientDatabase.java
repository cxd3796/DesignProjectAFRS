package com.flyboiz.afrs.Model;

import com.flyboiz.afrs.Controller.Commands.Query;

import java.util.HashMap;

public class ClientDatabase
{
	private HashMap<Integer, Client> clients = new HashMap<>();
	private int totalClientNum;
	private int curClientNum;

	public ClientDatabase()
	{
		totalClientNum = 0;
		curClientNum = 0;
	}

	public Query getLastQuery(int cid)
	{
		return clients.get(cid).getLastQuery();
	}

	public Query getLastUndoQuery(int cid)
	{
		return clients.get(cid).getLastUndo();
	}

	public Query getLastRedoQuery(int cid)
	{
		return clients.get(cid).getLastRedo();
	}

	public void moveLastUndoToRedo(int cid)
	{
		clients.get(cid).moveLastUndoToRedo();
	}

	public void moveLastRedoToUndo(int cid)
	{
		clients.get(cid).moveLastRedoToUndo();
	}

	public int connectClient()
	{
		clients.put(totalClientNum, new Client(totalClientNum));
		totalClientNum++;
		curClientNum++;
		return totalClientNum-1;
	}

	public void disconnectClient(int cid)
	{
		clients.remove(cid);
		curClientNum--;
	}

	public int currentNumberOfClients()
	{
		return curClientNum;
	}
}
