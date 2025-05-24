#include <iostream>
#include <vector>
#include <string>
#include "pidUtil.h"

using namespace std;

int main() {
    vector<int> pids;
    ErrStatus status;

    // 1. Call GetAllPids() and GetNameByPid() to print all pids and their names.
    status = GetAllPids(pids);
    if (status != Err_OK) {
        cerr << "Error: " << GetErrorMsg(status) << endl;
        return 1;
    }

    for (int pid : pids) {
        string name;
        status = GetNameByPid(pid, name);
        if (status == Err_OK) {
            cout << "PID: " << pid << ", Name: " << name << endl;
        } else {
            cerr << "PID: " << pid << ", Error: " << GetErrorMsg(status) << endl;
        }
    }

    // 2. Set pid to 1. Call GetNameByPid() and print the name of pid 1.
    int pid = 1;
    string name1;
    status = GetNameByPid(pid, name1);
    if (status == Err_OK) {
        cout << "Name for PID 1: " << name1 << endl;
    } else {
        cerr << "Error: " << GetErrorMsg(status) << endl;
    }

    // 3. Set name to "Lab2". Call GetPidByName() to get the pid of Lab2. Print it.
    string lookupName = "Lab2";
    int lookupPid;
    status = GetPidByName(lookupName, lookupPid);
    if (status == Err_OK) {
        cout << "PID for " << lookupName << ": " << lookupPid << endl;
    } else {
        cerr << "Error: " << GetErrorMsg(status) << endl;
    }

    // 4. Set name to "Lab22". Call GetPidByName() to get the pid (should fail).
    lookupName = "Lab22";
    status = GetPidByName(lookupName, lookupPid);
    if (status == Err_OK) {
        cout << "PID for " << lookupName << ": " << lookupPid << endl;
    } else {
        cerr << "Error: " << GetErrorMsg(status) << endl;
    }

    return 0;
}

