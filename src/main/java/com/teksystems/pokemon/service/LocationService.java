package com.teksystems.pokemon.service;

import com.teksystems.pokemon.database.dao.LocationDAO;
import com.teksystems.pokemon.database.entity.Location;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationDAO locationDao;

    public Double calculateDistance(Integer fromId, Integer toId) {
        final Location FROM = locationDao.findById(fromId);
        final Location TO = locationDao.findById(toId);

        final Point CENTER_FROM = centroid(parsePointList(FROM.getCoordinates()));
        final Point CENTER_TO = centroid(parsePointList(TO.getCoordinates()));

        return CENTER_FROM.distance(CENTER_TO) / 10;
    }

    private List<Point> parsePointList (String stringCoor) {
        return Arrays.stream(stringCoor.split(" ")).map(item -> {
            String[] coors = item.split(",");
            return new Point(Integer.parseInt(coors[0]), Integer.parseInt(coors[1]));
        }).toList();
    }

    private Point centroid(List<Point> points)  {
        int centroidX = 0, centroidY = 0;

        for(Point point : points) {
            centroidX += point.getX();
            centroidY += point.getY();
        }
        return new Point(centroidX / points.size(), centroidY / points.size());
    }
}