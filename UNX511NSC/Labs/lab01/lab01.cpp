/****************************************************************************** *** 
*	UNX511-Lab1 
*	I declare that this lab is my own work in accordance with Seneca Academic Policy.   
*	No part of this assignment has been copied manually or electronically from any other source *  
*    (including web sites) or distributed to other students. 
*  
*	Name: Lebna Noori Student ID: 157672205 Date: 2025-05-12 
* 
******************************************************************************
**/  
#include <iostream>
#include <fstream>
#include <algorithm>
#include <dirent.h> // for opendir, readdir, closedir, linux library, not available in windows
#include <cstring>
#include <string>
#include <sstream>
#include <cstdlib>

bool is_digits(const std::string& str) {
    return std::all_of(str.begin(), str.end(), ::isdigit);
}

void check_process_memory(const std::string& pid) {
    std::string status_path = "/proc/" + pid + "/status";
    std::ifstream status_file(status_path);
    if (!status_file.is_open())
        return;

    std::string line;
    std::string name;
    int vmrss_kb = 0;

    while (std::getline(status_file, line)) {
        if (line.find("Name:") == 0) {
            name = line.substr(6);
            name.erase(0, name.find_first_not_of(" \t"));
        } else if (line.find("VmRSS:") == 0) {
            std::istringstream iss(line.substr(6));
            iss >> vmrss_kb;
            break; // No need to read further
        }
    }

    if (vmrss_kb > 10000) {
        std::cout << "PID: " << pid << ", Name: " << name << ", VmRSS: " << vmrss_kb << " kB" << std::endl;
    }
}

int main() {
    DIR* proc_dir = opendir("/proc");
    if (!proc_dir) {
        perror("Cannot open /proc");
        return 1;
    }

    struct dirent* entry;
    while ((entry = readdir(proc_dir)) != nullptr) {
        if (entry->d_type == DT_DIR && is_digits(entry->d_name)) {
            check_process_memory(entry->d_name);
        }
    }

    closedir(proc_dir);
    return 0;
}
