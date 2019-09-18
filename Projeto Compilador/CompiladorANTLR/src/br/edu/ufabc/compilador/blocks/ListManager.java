package br.edu.ufabc.compilador.blocks;

import br.edu.ufabc.compilador.commands.CmdLoop;
import br.edu.ufabc.compilador.commands.Command;
import br.edu.ufabc.compilador.definitions.Variables;

import java.util.ArrayList;
import java.util.List;

public class ListManager {
    ArrayList<CmdLoop> list_loop;
    ArrayList<List> list_if;

    public ListManager()
    {
        this.list_loop = new ArrayList<CmdLoop>();
        this.list_if = new ArrayList<List>();
    }

    public void addLoop(CmdLoop loop)
    {
        list_loop.add(loop);
    }
    public void popLoop()
    {
        list_loop.remove(list_loop.size()-1);
    }
    public Variables loopGetVar(String varName)
    {
        for (int i = list_loop.size()-1; i>-1; i--)
        {
            CmdLoop loop = list_loop.get(i);
            Variables var = loop.loopGetVar(varName);
            if(var != null)
                return var;
        }
        return null;
    }
    public CmdLoop lastLoop()
    {
        return list_loop.get(list_loop.size()-1);
    }
    public boolean loopIsEmpty()
    {
        return list_loop.isEmpty();
    }

    public void addIf(List list_if)
    {
        this.list_if.add(list_if);
    }
    public void popIf()
    {
        list_if.remove(list_if.size()-1);
    }
}
