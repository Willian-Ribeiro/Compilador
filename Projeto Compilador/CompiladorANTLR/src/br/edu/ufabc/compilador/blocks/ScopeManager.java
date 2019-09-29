package br.edu.ufabc.compilador.blocks;

import br.edu.ufabc.compilador.commands.CmdIfElse;
import br.edu.ufabc.compilador.commands.CmdLoop;
import br.edu.ufabc.compilador.commands.CmdWhile;
import br.edu.ufabc.compilador.definitions.Variables;

import java.util.ArrayList;

public class ScopeManager {
    private static final ScopeManager instance = new ScopeManager();

    ArrayList<CmdLoop> scope_loop;
    ArrayList<CmdWhile> scope_while;
    ArrayList<CmdIfElse> scope_if;
    java.util.HashMap<String, Variables> mapaVar;

    private ScopeManager()
    {
        this.scope_loop = new ArrayList<CmdLoop>();
        this.scope_while = new ArrayList<CmdWhile>();
        this.scope_if = new ArrayList<CmdIfElse>();
    }

    public static ScopeManager getInstance()
    {
        return instance;
    } // return singleton class

    public void setMapaVar(java.util.HashMap<String, Variables> mapaVar)
    {
        this.mapaVar = mapaVar;
    }

    // for
    public void addLoop(CmdLoop loop)
    {
        scope_loop.add(loop);
    }
    public void popLoop()
    {
        // before removing, check if vars were used
        lastLoop().checkVarsAttributedUsed();
        scope_loop.remove(scope_loop.size()-1);
    }
    public Variables loopGetVar(String varName)
    {
        for (int i = scope_loop.size()-1; i>-1; i--)
        {
            CmdLoop loop = scope_loop.get(i);
            Variables var = loop.loopGetVar(varName);
            if(var != null)
                return var;
        }
        return null;
    }
    public CmdLoop lastLoop()
    {
        return scope_loop.get(scope_loop.size()-1);
    }
    public boolean loopIsEmpty()
    {
        return scope_loop.isEmpty();
    }


    // while
    public void addWhile(CmdWhile _while)
    {
        scope_while.add(_while);
    }
    public void popWhile()
    {
        lastWhile().checkVarsAttributedUsed();
        scope_while.remove(scope_while.size()-1);
    }
    public Variables whileGetVar(String varName)
    {
        for (int i = scope_while.size()-1; i>-1; i--)
        {
            CmdWhile _while = scope_while.get(i);
            Variables var = _while.whileGetVar(varName);
            if(var != null)
                return var;
        }
        return null;
    }
    public CmdWhile lastWhile()
    {
        return scope_while.get(scope_while.size()-1);
    }
    public boolean whileIsEmpty()
    {
        return scope_while.isEmpty();
    }

    // If
    public void addIf(CmdIfElse list_if)
    {
        this.scope_if.add(list_if);
    }
    public void popIf()
    {
        lastIfElse().checkVarsAttributedUsed();
        scope_if.remove(scope_if.size()-1);
    }
    public CmdIfElse lastIfElse()
    {
        return scope_if.get(scope_if.size()-1);
    }

    //all
    public void checkUsedAttributedVariables()
    {
        for (Variables var: mapaVar.values())
        {
            System.out.println("Var name: "+var.getName()+" used: "+var.getUsed()+" attributed: "+var.getAttributed()+"\n");
            if(!var.getAttributed())
                throw new RuntimeException("ERROR Variable " + var.getName() + " not attributed");
            if(!var.getUsed())
                throw new RuntimeException("WARNING? Variable " + var.getName() + " not being used");
        }
    }
}
