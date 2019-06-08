cmake_minimum_required(VERSION 3.5)
project(cplus)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES:)

add_executable(cplus ${SOURCE_FILES} MapSolver.cpp MapSolver.h MapSolverMain.cpp)