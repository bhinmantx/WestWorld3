package west.model;

public abstract class BaseGameEntity {
	//every entity must have a unique identifying number
	private int m_ID;
	private String name;
	//this is the next valid ID. Each time a BaseGameEntity is instantiated
	  //this value is updated

	static private int m_iNextValidID = 0;
	
	public BaseGameEntity(int ID, String name){
		setID(ID);
		this.name = name;
	}
	static public boolean minerAtGoldmine = false;
	static public boolean minerBeingRobbed = false;
	static public boolean minerWasRobbed = false;
	
	abstract public void update();
	private void setID(int val){
		if( val >= m_iNextValidID ) {
			m_ID = val;
			m_iNextValidID = m_ID + 1;
		}
	}
	public int ID(){return m_ID;}
	public String getName(){return name;}
/*
	/////Time to implement a better messaging system
	static private enum messageStates {
		isAtGoldmine, isBeingRobbed, isWasBeingRobbed
	}
	public boolean[] messages = {false,false,false};
	*/
	
	////Getters and setters for what passes for "messaging"
	///The entermineand search for nuggets state sets this as true
	///both being robbed, and the exit of enterMine set this as false
	public boolean isMinerAtGoldmine(){return minerAtGoldmine;}
	public void minerIsAtGoldmine(){minerAtGoldmine = true;	}
	public void minerLeavesGoldmine(){minerAtGoldmine = false;}

	///The troll prepAndSteal state sets these
	public boolean isMinerBeingRobbed(){return minerBeingRobbed;}
	public void minerIsBeingRobbed(){minerBeingRobbed = true;}
	public void minerIsNotBeingRobbed(){minerBeingRobbed = false;}

	public boolean wasMinerRobbed(){return minerWasRobbed;}
	public void minerWasBeingRobbed(){minerWasRobbed = true;}
	public void minerWasNotBeingRobbed(){minerWasRobbed = false;}
}
