package com.flyboiz.afrs.Model;

import com.flyboiz.afrs.Controller.Commands.Query;

import java.util.Stack;

public class Client
{
	private Stack<Query> queryStack = new Stack<>();
	private Stack<Query> undoStack = new Stack<>();
	private Stack<Query> redoStack = new Stack<>();
	private int cid;

	/**
	 * Constructor for client
	 * @param cid Takes in a client id
	 */
	public Client(int cid)
	{
		this.cid = cid;
	}

	/**
	 * Adds to the client's query stack with a query
	 * @param query
	 */
	public void addLastQuery(Query query)
	{
		queryStack.push(query);
	}

	public void addUndoQuery(Query query)
	{
		undoStack.push(query);
	}

	public void addRedoQuery(Query query)
	{
		redoStack.push(query);
	}

	public Query getLastQuery()
	{
		return queryStack.peek();
	}

	public Query getLastRedo(){
		if(redoStack.isEmpty()){
			return null;
		}
		return redoStack.peek();
	}

	public Query getLastUndo(){
		if(undoStack.isEmpty()){
			return null;
		}
		return undoStack.peek();
	}

	public void moveLastUndoToRedo()
	{
		Query query = undoStack.pop();
		redoStack.push(query);
	}

	public void moveLastRedoToUndo()
	{
		Query query = redoStack.pop();
		undoStack.push(query);
	}

	public Stack<Query> getUndoStack() {
		return undoStack;
	}

	public Stack<Query> getRedoStack() {
		return redoStack;
	}

	public int getClientID()
	{
		return cid;
	}
}
